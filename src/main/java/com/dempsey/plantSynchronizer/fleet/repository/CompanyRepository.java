package com.dempsey.plantSynchronizer.fleet.repository;

import com.dempsey.plantSynchronizer.fleet.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findOneByCompany(String dempseyWoodCivil);
}

