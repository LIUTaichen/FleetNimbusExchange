package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.fleet.config.Constants;
import com.dempsey.plantSynchronizer.fleet.domain.Category;
import com.dempsey.plantSynchronizer.fleet.domain.Plant;
import com.dempsey.plantSynchronizer.fleet.repository.CategoryRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.AssetSubGroupRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.AssetSubGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategorySyncService {
    private final CategoryRepository categoryRepository;
    private final AssetSubGroupRepository assetSubGroupRepository;
    private Logger log = LoggerFactory.getLogger(CategorySyncService.class);

    public CategorySyncService(CategoryRepository categoryRepository, AssetSubGroupRepository assetSubGroupRepository) {
        this.categoryRepository = categoryRepository;
        this.assetSubGroupRepository = assetSubGroupRepository;
    }

    public void diff(){
        List<AssetSubGroup> subGroups = assetSubGroupRepository.findAllByDiscontinuedFalse();
        List<Category> categories = categoryRepository.findAll();
        Map<String, Category> categoryMap = categories.stream().collect(Collectors.toMap(category -> category.getCategory(), category -> category ));
        Map<String, AssetSubGroup> subGroupMap = subGroups.stream()
                .collect(Collectors.toMap(subGroup -> subGroup.getDescription(), subGroup -> subGroup ));

        List<Category> toBeCreated = new ArrayList<Category>();
        subGroupMap.keySet().forEach(key -> {

            if(!categoryMap.containsKey(key)){
                try {
                    AssetSubGroup subGroup = subGroupMap.get(key);
                    log.info("found new subGroup : " + subGroup.toString());
                    Category category = convertToCategory(subGroup);
                    setAuditField(category);
                    log.info("inserting new category : " + category.toString());
                    categoryRepository.save(category);
                }
                catch (Exception e){
                    log.error("Encountered error when processing asset sub group " + key, e);
                }
            }
        });
    }

    public Category convertToCategory(AssetSubGroup sub){
        Category category = new Category();
        category.setCategory(sub.getDescription());
        category.setDescription(sub.getDetailedDescription());
        //category.setIsTrackedForInternalBilling(sub.getBillable());
        return category;
    }
    public void setAuditField(Category category){
        category.setCreatedBy(Constants.SYSTEM_ACCOUNT);
        category.setCreatedDate(Instant.now());
        category.setLastModifiedBy(Constants.SYSTEM_ACCOUNT);
        category.setLastModifiedDate(Instant.now());
    }

}
