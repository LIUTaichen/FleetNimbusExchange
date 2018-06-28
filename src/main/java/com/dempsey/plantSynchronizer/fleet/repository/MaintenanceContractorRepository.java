package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.MaintenanceContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the MaintenanceContractor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaintenanceContractorRepository extends JpaRepository<MaintenanceContractor, Long> {

    MaintenanceContractor findOneByName(String name);
}
