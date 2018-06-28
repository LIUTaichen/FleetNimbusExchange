package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.AssetSubGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  AssetSubGroupRepository extends CrudRepository<AssetSubGroup, String> {

    List<AssetSubGroup> findAllByDiscontinuedFalse();
}
