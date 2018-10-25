package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.fleet.config.Constants;
import com.dempsey.plantSynchronizer.fleet.domain.Category;
import com.dempsey.plantSynchronizer.fleet.domain.Company;
import com.dempsey.plantSynchronizer.fleet.repository.CategoryRepository;
import com.dempsey.plantSynchronizer.fleet.repository.CompanyRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.AssetSubGroupRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.ResouceOwnerRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.AssetSubGroup;
import com.dempsey.plantSynchronizer.nimbus.entity.CreditorContact;
import com.dempsey.plantSynchronizer.nimbus.entity.ResourceOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompanySyncService {

    private final ResouceOwnerRepository ownerRepository;
    private final CompanyRepository companyRepository;
    private Logger log = LoggerFactory.getLogger(CompanySyncService.class);

    public CompanySyncService(ResouceOwnerRepository ownerRepository, CompanyRepository companyRepository) {
        this.ownerRepository = ownerRepository;
        this.companyRepository = companyRepository;
    }

    public void diff(){
        log.info("Company sync started");
        List<ResourceOwner> creditors = ownerRepository.findAll();
        List<Company> companyList = companyRepository.findAll();
        Map<String, ResourceOwner> nimbusOwnerMap = creditors.stream().collect(Collectors.toMap(owner -> owner.getCreditorIndex(), owner -> owner ));
        Map<String, Company> companyMap = companyList.stream()
                .collect(Collectors.toMap(company -> company.getAbbreviation(), company -> company ));

        List<Company> toBeCreated = new ArrayList<>();
        nimbusOwnerMap.keySet().forEach(key -> {

            if(!companyMap.containsKey(key)){
                try {
                    ResourceOwner owner = nimbusOwnerMap.get(key);
                    log.info("found new creditor : " + owner.toString());
                    Company newCompany = convertToCompany(owner, owner.getContact());
                    setAuditField(newCompany);
                    log.info("inserting new category : " + newCompany.toString());
                    companyRepository.save(newCompany);
                }
                catch (Exception e){
                    log.error("Encountered error when processing asset sub group " + key, e);
                }
            }
        });
        log.info("Company sync finished");
    }

    public Company convertToCompany(ResourceOwner owner, CreditorContact contact){
        Company company = new Company();
        company.setAbbreviation(owner.getCreditorIndex());
        company.setCompany(contact.getCompany());
        company.setNotes(owner.getNotes());
        company.setPhone(contact.getPhone());
        company.setWebPage(contact.getWebPage());
        company.setIsActive(true);
        return company;
    }
    public void setAuditField(Company company){
        company.setCreatedBy(Constants.SYSTEM_ACCOUNT);
        company.setCreatedDate(Instant.now());
        company.setLastModifiedBy(Constants.SYSTEM_ACCOUNT);
        company.setLastModifiedDate(Instant.now());
    }
}
