package com.dempsey.plantSynchronizer.nimbus.util;

import com.dempsey.plantSynchronizer.nimbus.entity.Plant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlantsUtil {
    private static Logger log = LoggerFactory.getLogger(PlantsUtil.class);


    public static Plant findBetterPlant(Plant p1, Plant p2) {
        log.info("p1  " + p1.getItem_Name() + " " + p1.getOwner() + "   p2  " + p2.getItem_Name() + " " + p2.getOwner());
        if (p1.getMaintenanceUnits() == null) {
            log.info(" taking p2 because p1 has empty category");
            return p2;


        } else if (p2.getMaintenanceUnits() == null) {
            log.info(" taking p1 because p2 has empty category");
            return p1;
        }

        if (p1.getLast_Log_Date() != null && p2.getLast_Log_Date() != null) {

            if (p1.getLast_Log_Date().after(p2.getLast_Log_Date())) {
                log.info(" taking p1 because it has fresher last log date");
                return p1;
            } else {
                log.info(" taking p2 because it has fresher last log date");
                return p2;
            }
        } else {
            if (p1.getLast_Log_Date() != null) {
                log.info(" taking p1 because it has valid last log date, and p2 does not");
                return p1;
            } else {
                log.info(" taking p2 because it has valid last log date, and p1 does not");
                return p2;
            }
        }
    }
}
