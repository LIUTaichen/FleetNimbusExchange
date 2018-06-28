package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.nimbus.dao.PlantAllocationRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.PlantAllocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantAllocationService {

    private Logger log = LoggerFactory.getLogger(PlantAllocationService.class);

    @Autowired
    private PlantAllocationRepository plantAllocationRepository;

    public List<PlantAllocation> findAll(){
        List<PlantAllocation> resultList = new ArrayList();
         plantAllocationRepository.findAll().forEach(resultList::add);
        resultList.forEach(p -> log.info(p.toString()));

        return resultList;
    }

    public String getDepartment(Integer plantId) {
        String department = "";
        List<PlantAllocation> allocations = plantAllocationRepository.findAllByPlantIDAndActive(plantId, true);
        PlantAllocation bestAllocation = findBest(allocations);
        if(bestAllocation == null){
            return "";
        }

        return bestAllocation.getProject().getProjectID();

    }
    public PlantAllocation findBest(List<PlantAllocation> allocations){

        if(allocations == null || allocations.isEmpty()){
            return null;
        }

        PlantAllocation allocation = null;
        for(PlantAllocation alloc : allocations){
            if(alloc.getProject() == null){
                continue;
            }
            if(!alloc.getProject().isActive()){
                continue;
            }
            allocation = alloc;
        }
        return allocation;

    }

}
