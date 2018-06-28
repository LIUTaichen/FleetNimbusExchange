package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.OffHireRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the OffHireRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffHireRequestRepository extends JpaRepository<OffHireRequest, Long> {

}
