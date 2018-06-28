package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.PlantAllocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlantAllocationRepository extends CrudRepository<PlantAllocation, Integer> {
    List<PlantAllocation> findAllByPlantIDAndActive(Integer plantId, boolean b);
}
