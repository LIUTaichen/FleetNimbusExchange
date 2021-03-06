package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.fleet.config.Constants;
import com.dempsey.plantSynchronizer.fleet.domain.Category;
import com.dempsey.plantSynchronizer.fleet.domain.Company;
import com.dempsey.plantSynchronizer.fleet.domain.Plant;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.MaintenanceType;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.MeterUnit;
import com.dempsey.plantSynchronizer.fleet.repository.CategoryRepository;
import com.dempsey.plantSynchronizer.fleet.repository.CompanyRepository;
import com.dempsey.plantSynchronizer.fleet.repository.FleetPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.NimbusPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.ResouceOwnerRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.NimbusPlant;
import com.dempsey.plantSynchronizer.nimbus.entity.ResourceOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlantSyncService {

    public static final String DEMPSEY_WOOD_CIVIL = "Dempsey Wood Civil";
    private final NimbusPlantRepository nimbusPlantRepository;

    private final FleetPlantRepository fleetPlantRepository;

    private Logger log = LoggerFactory.getLogger(PlantSyncService.class);

    private final CategoryRepository categoryRepository;

    private final CompanyRepository companyRepository;

    private final ResouceOwnerRepository resouceOwnerRepository;

    private Map<String, Category> categoryMap;

    private Map<String, Company> companyMap;

    private Map<Integer, ResourceOwner> resourceOwnerMap;

    private Company dempseyWood;

    public PlantSyncService(NimbusPlantRepository nimbusPlantRepository, FleetPlantRepository fleetPlantRepository, CategoryRepository categoryRepository, CompanyRepository companyRepository, ResouceOwnerRepository resouceOwnerRepository) {
        this.nimbusPlantRepository = nimbusPlantRepository;
        this.fleetPlantRepository = fleetPlantRepository;
        this.categoryRepository = categoryRepository;
        this.companyRepository = companyRepository;
        this.resouceOwnerRepository = resouceOwnerRepository;
    }
    public List<Plant> diff(){
        log.debug("plant sync started");
        List<NimbusPlant> nimbusPlants = nimbusPlantRepository.findByFleetIdIsNotNullAndClosedFalse();
        log.debug("successfully fetched " +nimbusPlants.size() + " plants from nimbus");
        List<Plant> fleetPlants = fleetPlantRepository.findAll();
        log.debug("successfully fetched " +fleetPlants.size() + " plants from fleet");
        Map<String, Plant> fleetPlantMap = fleetPlants.stream().collect(Collectors.toMap(plant -> plant.getFleetId(), plant -> plant ));
        log.debug("constructed fleet plants map");
        Map<String, NimbusPlant> nimbusPlantMap = nimbusPlants.stream()
                .collect(Collectors.toMap(plant -> plant.getFleetId(), plant -> plant ));
        log.debug("constructed nimbus plants map");
        List<Plant> toBeCreated = new ArrayList<Plant>();
        List<Plant> toBeUpdated = new ArrayList<Plant>();
        nimbusPlantMap.keySet().forEach( key -> {
            log.debug("processing plantId " + key);
            log.debug("start to find nimbus plant from map ");
            NimbusPlant nimbusPlant = nimbusPlantMap.get(key);
            log.debug("searching for nimbus plant completed ");
            if(fleetPlantMap.containsKey(key)){
                Plant fleetPlant = fleetPlantMap.get(key);
                String originalSnapshot = getSnapshot(fleetPlant);
                log.debug("start to update fleet plant with nimbus data ");
                Plant updatedFromNimbus = updatePlantWithNimbusData(fleetPlant,nimbusPlant);
                log.debug("finished to update fleet plant with nimbus data ");
                String updatedSnapshot = getSnapshot(updatedFromNimbus);

                if(!originalSnapshot.equals(updatedSnapshot)){
                    log.info("found plant to be updated");
                    log.info("original: " +  originalSnapshot);
                    log.info("updated: " + updatedSnapshot);
                    toBeUpdated.add(updatedFromNimbus);
                }

            }else{
                log.info("plant not found: " +  nimbusPlantMap.get(key).toString());
                Plant plant = new Plant();
                log.debug("start to update fleet plant with nimbus data ");
                plant = updatePlantWithNimbusData(plant, nimbusPlant);
                log.debug("finished to update fleet plant with nimbus data ");
                setAuditField(plant);
                log.info("found plant to be created in fleet management: " + plant.toString());
                toBeCreated.add(plant);
            }
            log.debug("processing for plantId " + key + " completed");
        });


        log.info("number of plants to be created "+  toBeCreated.size());
        log.info("number of plants to be updated " + toBeUpdated.size());
        List<Plant> toBeSaved = toBeCreated;
        toBeSaved.addAll(toBeUpdated);
        return toBeSaved;
    }


    @Transactional
    public void insertNewPlants(){
        List<Plant> toBeSaved = diff();
        log.info("Saving " + toBeSaved.size() + " to fleet database");
        fleetPlantRepository.save(toBeSaved);
        log.info("Saving completed");
    }



    public Plant updatePlantWithNimbusData(Plant plant, NimbusPlant civilPlant){
        Category category = null;
        if(civilPlant.getAssetSubGroup() != null){
            category = getCategory(civilPlant.getAssetSubGroup().getDescription());
        }
        plant.setCategory(category);
        plant.setDescription(civilPlant.getDescription());
        plant.setFleetId(civilPlant.getFleetId());
        plant.setGpsDeviceSerial(civilPlant.getDeviceSerialNo());
        //plant.setHireStatus(civilPlant.getHireStatus());
        plant.setMaintenanceDueAt(civilPlant.getMaintenance_Due());
        if(civilPlant.getHub_Reading() != null && civilPlant.getHub_Reading() > 0){
               plant.setHubboReading(new Double(civilPlant.getHub_Reading()));
        }else{
            plant.setHubboReading(null);
        }




        MeterUnit meter = null;
        if("Hrs".equals(civilPlant.getMeterType())){
            meter = MeterUnit.HOUR;
//            if(civilPlant.getHoursReading() != null && civilPlant.getHoursReading() > 0 ){
//                plant.setMeterReading( civilPlant.getHoursReading());
//            }
            plant.setMaintenanceType(MaintenanceType.METER_BASED);

        }else if("Km".equals(civilPlant.getMeterType())){
            meter = MeterUnit.KM;
//            if(civilPlant.getLast_Log() != null && civilPlant.getLast_Log() > 0 ){
//                plant.setMeterReading( new Double(civilPlant.getLast_Log()));
//            }
            plant.setMaintenanceType(MaintenanceType.METER_BASED);

        }else {
            plant.setMaintenanceType(MaintenanceType.TIME_BASED);
        }
        plant.setMeterUnit(meter);



        plant.setNotes(civilPlant.getNotes());
        Company owner = null;
        if(civilPlant.getOwnerId() != null ){
            owner = getOwner(civilPlant.getOwnerId());
        }else{
            owner = getDempseyWood();
        }
        plant.setOwner(owner);
        if(civilPlant.getRegistration_Due_Date() != null){
            plant.setRegistrationDueDate(civilPlant.getRegistration_Due_Date().toInstant());
        }

        plant.setRego(civilPlant.getRegistration());
        plant.setRucDueAtKm(civilPlant.getRUC_Due());
        plant.setVin(civilPlant.getVinNumber());
        return plant;
    }

    public void setAuditField(Plant plant){
        plant.setCreatedBy(Constants.SYSTEM_ACCOUNT);
        plant.setCreatedDate(Instant.now());
        plant.setLastModifiedBy(Constants.SYSTEM_ACCOUNT);
        plant.setLastModifiedDate(Instant.now());
    }

    public String getSnapshot(Plant plant){
        StringBuilder sb = new StringBuilder();
        sb.append(plant.toString());
        String category = plant.getCategory() != null ? plant.getCategory().getId().toString() : "";
        sb.append("Category=" + category + " ");
        String owner = plant.getOwner() != null ? plant.getOwner().getId().toString() : "";
        sb.append("Owner=" + owner + " ");
        return sb.toString();
    }

    private Category getCategory(String category){
        if(categoryMap == null) {
            categoryMap = categoryRepository.findAll().stream().collect(Collectors.toMap(categoryItem -> categoryItem.getCategory(), categoryItem -> categoryItem ));
        }
        return categoryMap.get(category);
    }

    private Company getOwner(int id){
        if(resourceOwnerMap == null){
            resourceOwnerMap = resouceOwnerRepository.findAll().stream().collect(Collectors.toMap(owner -> owner.getId(), owner -> owner ));
        }

        if(companyMap == null ){
            companyMap = companyRepository.findAll().stream().collect(Collectors.toMap(company -> company.getAbbreviation(), company -> company ));
        }

        String creditorIndex = resourceOwnerMap.get(id).getCreditorIndex();
        return companyMap.get(creditorIndex);
    }

    private Company getDempseyWood(){
        if(dempseyWood == null) {
            dempseyWood = companyRepository.findOneByCompany(DEMPSEY_WOOD_CIVIL);
        }
        return dempseyWood;
    }
}
