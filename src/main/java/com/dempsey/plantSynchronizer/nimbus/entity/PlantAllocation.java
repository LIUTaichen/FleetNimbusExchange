package com.dempsey.plantSynchronizer.nimbus.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the Access_Plant_Allocation database table.
 * 
 */
@Entity
@Table(name="Plant_Allocation")
public class PlantAllocation implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="Active")
	private boolean active;

	@Column(name="Cost_Code")
	private Integer costCode;

	@Column(name="Date_Available")
	private Date dateAvailable;

	@Column(name="Date_Created")
	private Date dateCreated;

	@Column(name="Date_Required")
	private Date dateRequired;

	@Column(name="Day_Rate")
	private Double dayRate;

	@Column(name="dweek")
	private Date dWeek;


	@Column(name="Last_Modified")
	private String lastModified;

	@Column(name="Notes")
	private String notes;

	@Column(name="Personnel")
	private String personnel;

	@Column(name="personnel_fk")
	private Integer personnelFk;

	@Column(name="Plant_ID")
	private Integer plantID;

	@Column(name="PM")
	private String pm;


	@OneToOne
	@JoinColumn(name = "project_fk")
	private Project project;

	@Column(name="Scope")
	private String scope;
	@Transient
	private Timestamp SSMA_TimeStamp;

	@Column(name="State")
	private String state;

	@Column(name="Type_Of_Work")
	private String  typeOfWork;

	public PlantAllocation() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getCostCode() {
		return costCode;
	}

	public void setCostCode(Integer costCode) {
		this.costCode = costCode;
	}

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateRequired() {
		return dateRequired;
	}

	public void setDateRequired(Date dateRequired) {
		this.dateRequired = dateRequired;
	}

	public Double getDayRate() {
		return dayRate;
	}

	public void setDayRate(Double dayRate) {
		this.dayRate = dayRate;
	}

	public Date getdWeek() {
		return dWeek;
	}

	public void setdWeek(Date dWeek) {
		this.dWeek = dWeek;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public Integer getPersonnelFk() {
		return personnelFk;
	}

	public void setPersonnelFk(Integer personnelFk) {
		this.personnelFk = personnelFk;
	}

	public Integer getPlantID() {
		return plantID;
	}

	public void setPlantID(Integer plantID) {
		this.plantID = plantID;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}


	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Timestamp getSSMA_TimeStamp() {
		return SSMA_TimeStamp;
	}

	public void setSSMA_TimeStamp(Timestamp SSMA_TimeStamp) {
		this.SSMA_TimeStamp = SSMA_TimeStamp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "PlantAllocation{" +
                "id=" + id +
                ", active=" + active +
                ", costCode=" + costCode +
                ", dateAvailable=" + dateAvailable +
                ", dateCreated=" + dateCreated +
                ", dateRequired=" + dateRequired +
                ", dayRate=" + dayRate +
                ", dWeek=" + dWeek +
                ", lastModified='" + lastModified + '\'' +
                ", notes='" + notes + '\'' +
                ", personnel='" + personnel + '\'' +
                ", personnelFk=" + personnelFk +
                ", plantID=" + plantID +
                ", pm='" + pm + '\'' +
                ", project=" + project +
                ", scope='" + scope + '\'' +
                ", SSMA_TimeStamp=" + SSMA_TimeStamp +
                ", state='" + state + '\'' +
                ", typeOfWork='" + typeOfWork + '\'' +
                '}';
    }


}