package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.fleet.config.Constants;
import com.dempsey.plantSynchronizer.fleet.domain.Category;
import com.dempsey.plantSynchronizer.fleet.domain.Company;
import com.dempsey.plantSynchronizer.fleet.domain.Plant;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.MeterUnit;
import com.dempsey.plantSynchronizer.fleet.repository.CategoryRepository;
import com.dempsey.plantSynchronizer.fleet.repository.CompanyRepository;
import com.dempsey.plantSynchronizer.fleet.repository.FleetPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.NimbusPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.NimbusPlant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
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

    public PlantSyncService(NimbusPlantRepository nimbusPlantRepository, FleetPlantRepository fleetPlantRepository, CategoryRepository categoryRepository, CompanyRepository companyRepository) {
        this.nimbusPlantRepository = nimbusPlantRepository;
        this.fleetPlantRepository = fleetPlantRepository;
        this.categoryRepository = categoryRepository;
        this.companyRepository = companyRepository;
    }
    public List<Plant> diff(){
        List<NimbusPlant> nimbusPlants = nimbusPlantRepository.findByFleetIdIsNotNullAndClosedFalse();
        List<Plant> fleetPlants = fleetPlantRepository.findAll();
        Map<String, Plant> fleetPlantMap = fleetPlants.stream().collect(Collectors.toMap(plant -> plant.getFleetId(), plant -> plant ));
        Map<String, NimbusPlant> nimbusPlantMap = nimbusPlants.stream()
                .collect(Collectors.toMap(plant -> plant.getFleetId(), plant -> plant ));

        List<Plant> toBeCreated = new ArrayList<Plant>();
        List<Plant> toBeUpdated = new ArrayList<Plant>();
        nimbusPlantMap.keySet().forEach( key -> {
            NimbusPlant nimbusPlant = nimbusPlantMap.get(key);
            if(fleetPlantMap.containsKey(key)){
                Plant fleetPlant = fleetPlantMap.get(key);
                String originalSnapshot = getSnapshot(fleetPlant);
                Plant updatedFromNimbus = updatePlantWithNimbusData(fleetPlant,nimbusPlant);
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
                plant = updatePlantWithNimbusData(plant, nimbusPlant);
                setAuditField(plant);
                log.info("found plant to be created in fleet management: " + plant.toString());
                toBeCreated.add(plant);
            }
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
        fleetPlantRepository.save(toBeSaved);
    }



    public Plant updatePlantWithNimbusData(Plant plant, NimbusPlant civilPlant){
        Category category = null;
        if(civilPlant.getAssetSubGroup() != null){
            category = categoryRepository.findOneByCategory(civilPlant.getAssetSubGroup().getDescription());
        }
        plant.setCategory(category);
        plant.setDescription(civilPlant.getDescription());
        plant.setFleetId(civilPlant.getFleetId());
        plant.setGpsDeviceSerial(civilPlant.getDeviceSerialNo());
        //plant.setHireStatus(civilPlant.getHireStatus());
        plant.setMaintenanceDueAt(civilPlant.getMaintenance_Due());
//        plant.setHubboReading(civilPlant.getHub_Reading());
        MeterUnit meter = null;
        if("Hrs".equals(civilPlant.getMeterType())){
            meter = MeterUnit.HOUR;
        }else if("Km".equals(civilPlant.getMeterType())){
            meter = MeterUnit.KM;
        }
        plant.setMeterUnit(meter);
        plant.setNotes(civilPlant.getNotes());
        Company owner = null;
        if(civilPlant.getOwnerId() != null ){
            owner = companyRepository.findOne(civilPlant.getOwnerId().longValue());
        }else{
            owner = companyRepository.findOneByCompany(DEMPSEY_WOOD_CIVIL);
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
}
