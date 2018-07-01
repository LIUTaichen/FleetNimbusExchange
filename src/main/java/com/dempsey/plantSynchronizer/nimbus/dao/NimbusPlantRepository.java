package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.NimbusPlant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface NimbusPlantRepository extends CrudRepository<NimbusPlant, Integer> {
    List<NimbusPlant> findByFleetIdIsNotNullAndFleetIdIn(Set<String> fleetIdSet);
    List<NimbusPlant> findByFleetIdIsNotNull();

    List<NimbusPlant> findByFleetIdIsNotNullAndClosedFalse();

}
