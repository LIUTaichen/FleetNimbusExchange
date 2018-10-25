package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="u_JobsOpenCivil")
public class Job {
    @Id
    @Column(name="jobid", unique=true, nullable=false)
    private Integer id;

    @Column(name="jobnumber")
    private String jobNumber;

    @Column(name="Description")
    private String jobName;

    @Column(name="SiteAddress")
    private String siteAddress;

    @Column(name="status")
    private String status;


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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobNumber='" + jobNumber + '\'' +
                ", jobName='" + jobName + '\'' +
                ", siteAddress='" + siteAddress + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
