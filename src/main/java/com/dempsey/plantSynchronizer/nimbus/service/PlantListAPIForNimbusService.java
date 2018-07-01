package com.dempsey.plantSynchronizer.nimbus.service;


import com.dempsey.plantSynchronizer.nimbus.dao.ApiRequestRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.NimbusPlantRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.ApiRequest;
import com.dempsey.plantSynchronizer.nimbus.entity.NimbusPlant;
import com.dempsey.plantSynchronizer.nimbus.util.SheetAPIUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PlantListAPIForNimbusService {

    private Logger log = LoggerFactory.getLogger(PlantListAPIForNimbusService.class);

    @Value("${email.google.api}")
    private String serviceAccountEmail;

    @Value("${path.google.api.key}")
    private String apiKeyPath;

    @Value("${plants.sheet.id}")
    private String plantSheetId;

    @Value("${plants.sheet.range}")
    private String plantsRange;


    @Autowired
    private ApiRequestRepository apiRequestRepository;

    @Autowired
    private NimbusPlantRepository plantRepository;

    public Sheets getSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // TODO: Change placeholder below to generate authentication credentials. See
        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/drive.file"
        //   "https://www.googleapis.com/auth/spreadsheets"
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(apiKeyPath))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/spreadsheets"));


        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Plant List Synchronizer/0.1")
                .build();
    }

    public ValueRange loadSheet(){
        String spreadsheetId = plantSheetId;
        String range = plantsRange;
        try {
            Sheets sheetsService = getSheetsService();
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();

            List<List<Object>> values = response.getValues();
            if (values == null || values.size() == 0) {
                log.info("No data found.");
            } else {
                log.info("values found");

                log.info("number of entries: " + values.size());

            }
            return response;
        }
        catch(Exception e){
            log.error("error when reading data from google sheet", e);
            return null;
        }

    }

    public void updateRange( List<ValueRange> updates){
        try {
            BatchUpdateValuesRequest body = new BatchUpdateValuesRequest()
                    .setValueInputOption("RAW")
                    .setData(updates);
            BatchUpdateValuesResponse result =
                    getSheetsService().spreadsheets().values().batchUpdate(plantSheetId, body).execute();
            log.info("Result of sheet update: " + result.toPrettyString());
        }
        catch (Exception e){
            log.error("error when updating sheet", e);
        }
    }

    public void updateOneRange( List<ValueRange> updates){
        try {
            ValueRange update = updates.get(0);
            String range = updates.get(0).getRange();
            UpdateValuesResponse result =
                    getSheetsService().spreadsheets().values().update(plantSheetId, range, update ).setValueInputOption("RAW").execute();
            log.info("Result of sheet update: " + result.toPrettyString());
        }
        catch (Exception e){
            log.error("error when updating sheet", e);
        }
    }

    public List<ValueRange> generateUpdate(Map<String, NimbusPlant> plantMap, List<String> headers, List<Map<String, String>> converted){
        List<ValueRange> updatesRequired = new ArrayList<ValueRange>();
        Map<Integer, String> projectMap = new HashMap<Integer, String>();

        for(Map<String, String> row: converted){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            String fleetId = row.get("Plant #");
            NimbusPlant plantInNimbus = plantMap.get(fleetId);
            if(plantInNimbus == null){
                continue;
            }else{
                Map<String, String> nimbusValueMap = new HashMap<String, String>();
                nimbusValueMap.put("Rego Due Date", plantInNimbus.getRegistration_Due_Date() != null ? sdf.format(plantInNimbus.getRegistration_Due_Date()) : "");
                nimbusValueMap.put("Last Log", plantInNimbus.getLast_Log() != null ? plantInNimbus.getLast_Log().toString() : "");
                nimbusValueMap.put("Last Log Date", plantInNimbus.getLast_Log_Date() != null ? sdf.format(plantInNimbus.getLast_Log_Date()) : "");
                nimbusValueMap.put("Maintenance Due",plantInNimbus.getMaintenance_Due() != null ?  plantInNimbus.getMaintenance_Due().toString(): "");
                nimbusValueMap.put("Maintenance Units", plantInNimbus.getMeterType() != null ? plantInNimbus.getMeterType(): "");
                nimbusValueMap.put("Units Til Maintenance", plantInNimbus.getUnits_Til_Maintenance() != null ? plantInNimbus.getUnits_Til_Maintenance().toString(): "");
                nimbusValueMap.put("WOF Due", plantInNimbus.getCert_Due() != null ? sdf.format(plantInNimbus.getCert_Due()): "");
                nimbusValueMap.put("Hub KM",plantInNimbus.getHub_Reading() != null ?  plantInNimbus.getHub_Reading().toString(): "");
                nimbusValueMap.put("RUC Due", plantInNimbus.getRUC_Due() != null ? plantInNimbus.getRUC_Due().toString(): "");
                //for owner


                //for  job no

                String department = plantInNimbus.getLast_Log_Project();
                if(department!= null &&!department.isEmpty()){
                    nimbusValueMap.put("Department", department);
                }


                nimbusValueMap.keySet().forEach(key -> {
                    if(!key.equals("Department")){
                        return;
                    }
                    log.info("checking department");
                    String oldValue = row.get(key);
                    String newValue = nimbusValueMap.get(key);
                    if(!oldValue.equals(newValue) && !newValue.isEmpty()){
                        log.info("found difference in row " + (converted.indexOf(row) + 2) + " Column : " + key + " , original value :  " + oldValue
                                +  " , new value from Nimbus : " + newValue);

                        ValueRange valueRange = new ValueRange();
                        Integer columnIndex = headers.indexOf(key) + 1;
                        Integer rowNumber = converted.indexOf(row) + 2;


                        String range = SheetAPIUtil.getRangeString(columnIndex, rowNumber);
                        valueRange.setRange("Plants!" + range);
                        List<List<Object>> outerList = new ArrayList<List<Object>>();
                        List<Object> innerList = new ArrayList<>();
                        innerList.add(newValue);
                        outerList.add(innerList);
                        valueRange.setValues(outerList);
                        updatesRequired.add(valueRange);
                    }

                });
            }
        }
        return updatesRequired;

    }

    public void syncPlantListWithNimbus(){
        log.debug("calling sheet service");
        ValueRange range = this.loadSheet();
        List<String> headers = SheetAPIUtil.getHeaders(range.getValues().get(0));
        range.getValues().remove(0);
        List<Map<String, String>> converted = SheetAPIUtil.convertRange(range.getValues(), headers);
        Set<String> fleetIdInSheet = new HashSet<String>();
        for(Map<String, String> map: converted){
            if(!map.get("Plant #").isEmpty()){
                fleetIdInSheet.add(map.get("Plant #"));
            }
        }
        log.debug("calling plantService");
        List<NimbusPlant> plants = plantRepository.findByFleetIdIsNotNullAndFleetIdIn(fleetIdInSheet);

        Map<String, NimbusPlant> plantMap=   plants.stream().collect(Collectors.toMap(NimbusPlant::getFleetId, p -> p, (s , a) -> {
            return s;
        }));

        log.info("size of plant list : " + plants.size());



        List<ValueRange> toBeUpdated = this.generateUpdate(plantMap,headers, converted);
        if(toBeUpdated.isEmpty()){
            log.info("No update needs to be done. Plant list in sync with Nimbus.");
        }else{
            log.info("Calling sheets api to update plant list.");
            for(ValueRange item: toBeUpdated){
                try {
                    log.info(item.toPrettyString());
                }catch(Exception e){
                    log.error("error when printing rangeitem", e);
                }
            }
            this.updateRange(toBeUpdated);
        }

    }

    public List<ValueRange> generateValueRangeForAllocation(){


        log.debug("calling sheet service");
        ValueRange range = this.loadSheet();
        List<String> headers = SheetAPIUtil.getHeaders(range.getValues().get(0));
        range.getValues().remove(0);
        List<Map<String, String>> converted = SheetAPIUtil.convertRange(range.getValues(), headers);
        Set<String> fleetIdInSheet = new HashSet<String>();
        for(Map<String, String> map: converted){
            if(!map.get("Plant #").isEmpty()){
                fleetIdInSheet.add(map.get("Plant #"));
            }
        }

        List<ValueRange> updatesRequired = new ArrayList<ValueRange>();

        for(int i = 0; i<converted.size(); i++){
            Map<String, String> valueMap = converted.get(i);
            String plantNumber = valueMap.get("Plant #");
            ApiRequest request = apiRequestRepository.findTopByJobIdIsNotNullAndDatasetAndAssetFleetIdOrderByTimestampDesc("Office", plantNumber);
            if(request == null){
               continue;
            }

            String jobNo = request.getJob().getJobNumber();
            log.info("found site " +  jobNo + " for plant : " + plantNumber + " This record is in row " + (i + 2));

            if(jobNo.equals(valueMap.get("Department"))){
                log.info("This is the same as the previous allocation. No need to update sheet");
                continue;
            }

            Integer columnIndex = headers.indexOf("Department") + 1;
            Integer rowNumber = i + 2;

            ValueRange valueRange = new ValueRange();
            String rangeToUpdate = SheetAPIUtil.getRangeString(columnIndex, rowNumber);
            valueRange.setRange("Plants!" + rangeToUpdate);
            List<List<Object>> outerList = new ArrayList<List<Object>>();
            List<Object> innerList = new ArrayList<>();
            innerList.add(jobNo);
            outerList.add(innerList);
            valueRange.setValues(outerList);
            updatesRequired.add(valueRange);

        }

        return updatesRequired;

    }

    public void pushUpdates(List<ValueRange> toBeUpdated){
        if(toBeUpdated.isEmpty()){
            log.info("No update needs to be done. Plant list in sync with Nimbus.");
        }else{
            log.info("Calling sheets api to update plant list.");
            for(ValueRange item: toBeUpdated){
                try {
                    log.info(item.toPrettyString());
                }catch(Exception e){
                    log.error("error when printing rangeitem", e);
                }
            }
            this.updateRange(toBeUpdated);
        }
    }

    public void updatePlantLocation(){
        this.pushUpdates(this.generateValueRangeForAllocation());
    }


}
