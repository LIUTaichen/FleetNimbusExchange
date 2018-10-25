package com.dempsey.plantSynchronizer.nimbus.service;

import com.dempsey.plantSynchronizer.fleet.config.Constants;
import com.dempsey.plantSynchronizer.fleet.domain.Project;

import com.dempsey.plantSynchronizer.fleet.repository.FleetProjectRepository;
import com.dempsey.plantSynchronizer.nimbus.dao.JobRepository;
import com.dempsey.plantSynchronizer.nimbus.entity.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectSyncService {

    private final JobRepository nimbusJobRepository;
    private final FleetProjectRepository fleetProjectRepository;
    private Logger log = LoggerFactory.getLogger(ProjectSyncService.class);
    private static final String[] ACTIVE_CIVIL_JOB_STATUS = {"JL", "JA"};


    public ProjectSyncService(JobRepository nimbusJobRepository, FleetProjectRepository fleetProjectRepository) {
        this.nimbusJobRepository = nimbusJobRepository;
        this.fleetProjectRepository = fleetProjectRepository;
    }

    public void diff(){
        log.info("Project sync started");
        List<Job> activeCivilJobs = nimbusJobRepository.findByStatusIn(ACTIVE_CIVIL_JOB_STATUS);
        List<Project> projects = fleetProjectRepository.findAll();
        Map<String, Job> nimbusJobsMap = activeCivilJobs.stream().collect(Collectors.toMap(job -> job.getJobNumber(), job -> job ));
        Map<String, Project> projectsMap = projects.stream()
                .collect(Collectors.toMap(project -> project.getJobNo(), project -> project ));

        List<Project> toBeCreated = new ArrayList<>();
        nimbusJobsMap.keySet().forEach(key -> {

            if(!projectsMap.containsKey(key)){
                try {
                    Job job = nimbusJobsMap.get(key);
                    log.info("found new job : " + job.toString());
                    Project newProject = convertToProject(job);
                    setAuditField(newProject);
                    log.info("inserting new project : " + newProject.toString());
                    fleetProjectRepository.save(newProject);
                }
                catch (Exception e){
                    log.error("Encountered error when processing asset sub group " + key, e);
                }
            }
        });
        log.info("Project sync finished");
    }

    public Project convertToProject(Job job){
        Project newProject = new Project();
        newProject.setJobNo(job.getJobNumber());
        newProject.setName(job.getJobName());
        newProject.setLocation(job.getSiteAddress());
        newProject.setIsActive(true);
        return newProject;
    }
    public void setAuditField(Project project){
        project.setCreatedBy(Constants.SYSTEM_ACCOUNT);
        project.setCreatedDate(Instant.now());
        project.setLastModifiedBy(Constants.SYSTEM_ACCOUNT);
        project.setLastModifiedDate(Instant.now());
    }
}
