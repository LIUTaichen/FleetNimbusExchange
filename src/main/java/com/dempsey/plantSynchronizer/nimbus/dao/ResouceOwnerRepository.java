package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.ResourceOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResouceOwnerRepository  extends JpaRepository<ResourceOwner, Integer> {
}
