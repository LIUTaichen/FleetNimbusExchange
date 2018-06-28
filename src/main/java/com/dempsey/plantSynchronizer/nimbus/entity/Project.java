package com.dempsey.plantSynchronizer.nimbus.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@Table(name="Project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="Active", nullable=false)
	private boolean active;

	@Column(name="Client")
	private Integer client;

	@Column(name="Date_Created", length=1)
	private String dateCreated;

	@Column(name="dcurrent_Week")
	private Date dCurrentWeek;

	@Column(name="End_Date")
	private Date endDate;

	@Column(name="Last_Modified", length=1)
	private String lastModified;

	@Column(name="Last_Submitted_By")
	private String lastSubmitted_By;

	@Column(name="Location")
	private String location;

	@Column(name="Notes")
	private String notes;

	@Column(name="On_Hold", nullable=false)
	private boolean onHold;

	@Column(name="Project_Details", nullable=false)
	private String projectDetails;

	@Column(name="Project_ID")
	private String projectID;

	@Column(name="Project_Manager")
	private Integer projectManager;

	@Column(name="Project_Name")
	private String projectName;

	@Column(name="QS")
	private Integer qs;

	@Column(name="Show_On_Civil_Jobs_List", nullable=false)
	private boolean showOnCivilJobsList;

	@Column(name="Start_Date")
	private Date startDate;

	public Project() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getdCurrentWeek() {
		return dCurrentWeek;
	}

	public void setdCurrentWeek(Date dCurrentWeek) {
		this.dCurrentWeek = dCurrentWeek;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getLastSubmitted_By() {
		return lastSubmitted_By;
	}

	public void setLastSubmitted_By(String lastSubmitted_By) {
		this.lastSubmitted_By = lastSubmitted_By;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isOnHold() {
		return onHold;
	}

	public void setOnHold(boolean onHold) {
		this.onHold = onHold;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public Integer getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Integer projectManager) {
		this.projectManager = projectManager;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getQs() {
		return qs;
	}

	public void setQs(Integer qs) {
		this.qs = qs;
	}

	public boolean isShowOnCivilJobsList() {
		return showOnCivilJobsList;
	}

	public void setShowOnCivilJobsList(boolean showOnCivilJobsList) {
		this.showOnCivilJobsList = showOnCivilJobsList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", active=" + active +
				", client=" + client +
				", dateCreated='" + dateCreated + '\'' +
				", dCurrentWeek=" + dCurrentWeek +
				", endDate=" + endDate +
				", lastModified='" + lastModified + '\'' +
				", lastSubmitted_By='" + lastSubmitted_By + '\'' +
				", location='" + location + '\'' +
				", notes='" + notes + '\'' +
				", onHold=" + onHold +
				", projectDetails='" + projectDetails + '\'' +
				", projectID='" + projectID + '\'' +
				", projectManager=" + projectManager +
				", projectName='" + projectName + '\'' +
				", qs=" + qs +
				", showOnCivilJobsList=" + showOnCivilJobsList +
				", startDate=" + startDate +
				'}';
	}
}