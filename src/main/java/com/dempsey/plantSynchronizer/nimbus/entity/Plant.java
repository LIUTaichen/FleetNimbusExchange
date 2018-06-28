package com.dempsey.plantSynchronizer.nimbus.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Plant database table.
 * 
 */
@Entity
@Table(name="Plant")
@NamedQuery(name="Plant.findAll", query="SELECT p FROM Plant p")
public class Plant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="Account_Code")
	private String account_Code;

	@Column(name="Active", nullable=false)
	private boolean active;

	@Column(name="Assigned_Contractor")
	private Integer assigned_Contractor;

	@Column(name="Category")
	private Integer category;

	@Column(name="Cert_Due")
	private Date cert_Due;

	@Column(name="Date_Created", nullable=false, length=1)
	private String date_Created;

	@Column(name="Date_Of_Manufacture")
	private String date_Of_Manufacture;

	@Column(name="Description")
	private String description;

	@Column(name="Fleet_ID")
	private String fleetId;

	@Column(name="Grouping_ID", precision=28, scale=6)
	private Integer grouping_ID;

	@Column(name="Hub_Reading", precision=28, scale=6)
	private Integer hub_Reading;

	@Column(name="Image")
	private Integer image;

	@Column(name="Item_Name", nullable=false)
	private String item_Name;

	@Column(name="Last_Log", precision=28, scale=6)
	private Integer last_Log;

	@Column(name="Last_Log_Date")
	private Date last_Log_Date;

	@Column(name="Last_Log_Delta", precision=28, scale=6)
	private Integer last_Log_Delta;

	@Column(name="Last_Log_Operator")
	private Integer last_Log_Operator;

	@Column(name="Last_Log_Project")
	private Integer last_Log_Project;

	@Column(name="Last_Modified", length=1)
	private String last_Modified;

	@Column(name="Load_Capacity")
	private Integer load_Capacity;

	@Column(name="Load_Hourly_Rate", precision=28, scale=6)
	private Integer load_Hourly_Rate;

	@Column(name="Maint_notes")
	private String maint_notes;

	@Column(name="Maintenance_Due")
	private Integer Maintenance_Due;

	@Column(name="maintenanceUnits")
	private String maintenanceUnits;



	@Column(name="Notes")
	private String notes;

	@Column(name="Owner", nullable=false)
	private Integer owner;

	@Column(name="Owner_Abbreviation")
	private String owner_Abbreviation;

	@Column(name="Purchase_Date")
	private Date purchase_Date;

	@Column(name="Rate_8hr", precision=28, scale=6)
	private Integer rate_8hr;

	@Column(name="Registration")
	private String registration;

	@Column(name="Registration_Due_Date")
	private Date registration_Due_Date;

	@Column(precision=28, scale=6)
	private Integer RUC_Due;

	@Column(name="Tank_Size")
	private Integer tank_Size;

	@Column(name="Units_Til_Maintenance", precision=28, scale=6)
	private Integer units_Til_Maintenance;

	@Column(name="VIN")
	private String vin;

	public Plant() {
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

	public String getAccount_Code() {
		return account_Code;
	}

	public void setAccount_Code(String account_Code) {
		this.account_Code = account_Code;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getAssigned_Contractor() {
		return assigned_Contractor;
	}

	public void setAssigned_Contractor(Integer assigned_Contractor) {
		this.assigned_Contractor = assigned_Contractor;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Date getCert_Due() {
		return cert_Due;
	}

	public void setCert_Due(Date cert_Due) {
		this.cert_Due = cert_Due;
	}

	public String getDate_Created() {
		return date_Created;
	}

	public void setDate_Created(String date_Created) {
		this.date_Created = date_Created;
	}

	public String getDate_Of_Manufacture() {
		return date_Of_Manufacture;
	}

	public void setDate_Of_Manufacture(String date_Of_Manufacture) {
		this.date_Of_Manufacture = date_Of_Manufacture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFleetId() {
		return fleetId;
	}

	public void setFleetId(String fleetId) {
		this.fleetId = fleetId;
	}

	public Integer getGrouping_ID() {
		return grouping_ID;
	}

	public void setGrouping_ID(Integer grouping_ID) {
		this.grouping_ID = grouping_ID;
	}

	public Integer getHub_Reading() {
		return hub_Reading;
	}

	public void setHub_Reading(Integer hub_Reading) {
		this.hub_Reading = hub_Reading;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

	public String getItem_Name() {
		return item_Name;
	}

	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}

	public Integer getLast_Log() {
		return last_Log;
	}

	public void setLast_Log(Integer last_Log) {
		this.last_Log = last_Log;
	}

	public Date getLast_Log_Date() {
		return last_Log_Date;
	}

	public void setLast_Log_Date(Date last_Log_Date) {
		this.last_Log_Date = last_Log_Date;
	}

	public Integer getLast_Log_Delta() {
		return last_Log_Delta;
	}

	public void setLast_Log_Delta(Integer last_Log_Delta) {
		this.last_Log_Delta = last_Log_Delta;
	}

	public Integer getLast_Log_Operator() {
		return last_Log_Operator;
	}

	public void setLast_Log_Operator(Integer last_Log_Operator) {
		this.last_Log_Operator = last_Log_Operator;
	}

	public Integer getLast_Log_Project() {
		return last_Log_Project;
	}

	public void setLast_Log_Project(Integer last_Log_Project) {
		this.last_Log_Project = last_Log_Project;
	}

	public String getLast_Modified() {
		return last_Modified;
	}

	public void setLast_Modified(String last_Modified) {
		this.last_Modified = last_Modified;
	}

	public Integer getLoad_Capacity() {
		return load_Capacity;
	}

	public void setLoad_Capacity(Integer load_Capacity) {
		this.load_Capacity = load_Capacity;
	}

	public Integer getLoad_Hourly_Rate() {
		return load_Hourly_Rate;
	}

	public void setLoad_Hourly_Rate(Integer load_Hourly_Rate) {
		this.load_Hourly_Rate = load_Hourly_Rate;
	}

	public String getMaint_notes() {
		return maint_notes;
	}

	public void setMaint_notes(String maint_notes) {
		this.maint_notes = maint_notes;
	}

	public Integer getMaintenance_Due() {
		return Maintenance_Due;
	}

	public void setMaintenance_Due(Integer Maintenance_Due) {
		this.Maintenance_Due = Maintenance_Due;
	}

	public String getMaintenanceUnits() {
		return maintenanceUnits;
	}

	public void setMaintenanceUnits(String Maintenance_Units) {
		this.maintenanceUnits = Maintenance_Units;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getOwner_Abbreviation() {
		return owner_Abbreviation;
	}

	public void setOwner_Abbreviation(String owner_Abbreviation) {
		this.owner_Abbreviation = owner_Abbreviation;
	}

	public Date getPurchase_Date() {
		return purchase_Date;
	}

	public void setPurchase_Date(Date purchase_Date) {
		this.purchase_Date = purchase_Date;
	}

	public Integer getRate_8hr() {
		return rate_8hr;
	}

	public void setRate_8hr(Integer rate_8hr) {
		this.rate_8hr = rate_8hr;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public Date getRegistration_Due_Date() {
		return registration_Due_Date;
	}

	public void setRegistration_Due_Date(Date registration_Due_Date) {
		this.registration_Due_Date = registration_Due_Date;
	}

	public Integer getRUC_Due() {
		return RUC_Due;
	}

	public void setRUC_Due(Integer RUC_Due) {
		this.RUC_Due = RUC_Due;
	}

	public Integer getTank_Size() {
		return tank_Size;
	}

	public void setTank_Size(Integer tank_Size) {
		this.tank_Size = tank_Size;
	}

	public Integer getUnits_Til_Maintenance() {
		return units_Til_Maintenance;
	}

	public void setUnits_Til_Maintenance(Integer units_Til_Maintenance) {
		this.units_Til_Maintenance = units_Til_Maintenance;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Override
	public String toString() {
		return "Plant{" +
				"id=" + id +
				", account_Code='" + account_Code + '\'' +
				", active=" + active +
				", assigned_Contractor=" + assigned_Contractor +
				", category=" + category +
				", cert_Due=" + cert_Due +
				", date_Created='" + date_Created + '\'' +
				", date_Of_Manufacture='" + date_Of_Manufacture + '\'' +
				", description='" + description + '\'' +
				", fleetId='" + fleetId + '\'' +
				", grouping_ID=" + grouping_ID +
				", hub_Reading=" + hub_Reading +
				", image=" + image +
				", item_Name='" + item_Name + '\'' +
				", last_Log=" + last_Log +
				", last_Log_Date=" + last_Log_Date +
				", last_Log_Delta=" + last_Log_Delta +
				", last_Log_Operator=" + last_Log_Operator +
				", last_Log_Project=" + last_Log_Project +
				", last_Modified='" + last_Modified + '\'' +
				", load_Capacity=" + load_Capacity +
				", load_Hourly_Rate=" + load_Hourly_Rate +
				", maint_notes='" + maint_notes + '\'' +
				", Maintenance_Due=" + Maintenance_Due +
				", maintenanceUnits='" + maintenanceUnits + '\'' +
				", notes='" + notes + '\'' +
				", owner=" + owner +
				", owner_Abbreviation='" + owner_Abbreviation + '\'' +
				", purchase_Date=" + purchase_Date +
				", rate_8hr=" + rate_8hr +
				", registration='" + registration + '\'' +
				", registration_Due_Date=" + registration_Due_Date +
				", RUC_Due=" + RUC_Due +
				", tank_Size=" + tank_Size +
				", units_Til_Maintenance=" + units_Til_Maintenance +
				", vin='" + vin + '\'' +
				'}';
	}
}