package com.dempsey.plantSynchronizer.nimbus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class JobScheduleService {

    private static final Logger log = LoggerFactory.getLogger(JobScheduleService.class);


    @Autowired
    private PlantListAPIService apiService;

    //@Scheduled(cron="0 0 6-19 *  * MON-FRI", zone="NZ")
    public void generateDailyLoadCountReport() {
        log.info("running scheduled synchronization");
        apiService.syncPlantListWithShirley();
        log.info("scheduled synchronization finished");
    }


}
