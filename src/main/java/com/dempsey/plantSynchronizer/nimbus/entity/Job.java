package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="jobs")
public class Job {
    @Id
    @Column(name="jobid", unique=true, nullable=false)
    private Integer id;

    @Column(name="jobnumber")
    private String jobNumber;

    @OneToMany(mappedBy = "job")
    private Set<ApiRequest> requests = new HashSet<ApiRequest>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Set<ApiRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<ApiRequest> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobNumber='" + jobNumber + '\'' +
                '}';
    }
}
