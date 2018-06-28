package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.Plant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

      public interface PlantRepository  extends CrudRepository<Plant, Integer> {

      public List<Plant> findByFleetIdIsNotNull();
      public List<Plant> findByFleetIdIsNotNullAndActiveTrueAndFleetIdIn(Set<String> fleetIdSet);

}
