package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.PlantLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the PlantLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantLogRepository extends JpaRepository<PlantLog, Long> {

}
