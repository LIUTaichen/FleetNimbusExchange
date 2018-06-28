package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.nimbus.dao.PlantRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> getPlantsWith(){
        List<Plant> plantList;
        //plantList = new ArrayList<Plant> (plantRepository.findByFleetIdIsNotNullAndActiveTrue());
        return null;
    }
}
