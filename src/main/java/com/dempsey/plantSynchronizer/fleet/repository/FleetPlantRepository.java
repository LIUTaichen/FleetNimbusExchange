package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Plant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FleetPlantRepository extends JpaRepository<Plant, Long>, JpaSpecificationExecutor<Plant> {

    List<Plant> findByGpsDeviceSerialIsNotNull();

}
