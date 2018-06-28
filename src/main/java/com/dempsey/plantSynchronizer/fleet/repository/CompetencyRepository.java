package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.Competency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Competency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetencyRepository extends JpaRepository<Competency, Long> {

}
