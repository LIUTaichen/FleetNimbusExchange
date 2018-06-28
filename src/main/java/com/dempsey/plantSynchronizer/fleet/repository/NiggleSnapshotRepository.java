package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.NiggleSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the NiggleSnapshot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiggleSnapshotRepository extends JpaRepository<NiggleSnapshot, Long>, JpaSpecificationExecutor<NiggleSnapshot> {

}
