package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.Niggle;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.Priority;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Niggle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiggleRepository extends JpaRepository<Niggle, Long>, JpaSpecificationExecutor<Niggle> {

    List<Niggle> findByStatusAndPriority(Status status, Priority priority);

}
