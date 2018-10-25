package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository  extends JpaRepository<Job, Integer> {
    List<Job> findByStatusIn(String[] activeCivilJobStatus);
}
