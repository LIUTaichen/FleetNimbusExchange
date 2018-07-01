package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="apirequests")
public class ApiRequest {
    @Id
    @Column(name="requestid", unique=true, nullable=false)
    private Integer requestId;

    @Column(name="requestemployee")
    private String requestEmployee;
    @Column(name="requesttimestamp")
    private Date requestTimestamp;
    @Column(name="dataset")
    private String dataset;
    @Column(name="apitransid")
    private Integer apiTransID;
    @Column(name="timestamp")
    private Date timestamp;
    @Column(name="plantnumber")
    private String plantNumber;
    @Column(name="rego")
    private String rego;
    @Column(name="site")
    private String site;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @Column(name="address")
    private String address;
    @Column(name="hours")
    private Double hours;
    @Column(name="nextwofdue")
    private Date nextWofDue;
    @Column(name="nextregodue")
    private Date nextRegoDue;
    @Column(name="nextrucduedate")
    private Date nextRUCDueDate;
    @Column(name="nextrucduekm")
    private Double nextRUCDueKm;
    @Column(name="groupname")
    private String groupName;

    @Column(name="vehicleassettype")
    private String vehicleAssetType;
    @Column(name="odometer")
    private Double odometer;
    @Column(name="nextservicetype")
    private String nextServiceType;
    @Column(name="hubo")
    private Double hubo;
    @Column(name="rucodotype")
    private String rucOdoType;
    @Column(name="deviceid")
    private String deviceId;
    @Column(name="nextserviceestdate")
    private Date nextServiceEstDate;

    @ManyToOne
    @JoinColumn(name = "u_editorassetitemid")
    private NimbusPlant asset;

    @Column(name="u_updateddatetime")
    private Date u_UpdatedDateTime;
    @Column(name="nextservicekm")
    private Double nextServiceKM;

    @ManyToOne
    @JoinColumn(name = "u_jobid")
    private Job job;

    @Column(name="u_employee", length=8)
    private String u_Employee;


    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestEmployee() {
        return requestEmployee;
    }

    public void setRequestEmployee(String requestEmployee) {
        this.requestEmployee = requestEmployee;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public Integer getApiTransID() {
        return apiTransID;
    }

    public void setApiTransID(Integer apiTransID) {
        this.apiTransID = apiTransID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public String getRego() {
        return rego;
    }

    public void setRego(String rego) {
        this.rego = rego;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Date getNextWofDue() {
        return nextWofDue;
    }

    public void setNextWofDue(Date nextWofDue) {
        this.nextWofDue = nextWofDue;
    }

    public Date getNextRegoDue() {
        return nextRegoDue;
    }

    public void setNextRegoDue(Date nextRegoDue) {
        this.nextRegoDue = nextRegoDue;
    }

    public Date getNextRUCDueDate() {
        return nextRUCDueDate;
    }

    public void setNextRUCDueDate(Date nextRUCDueDate) {
        this.nextRUCDueDate = nextRUCDueDate;
    }

    public Double getNextRUCDueKm() {
        return nextRUCDueKm;
    }

    public void setNextRUCDueKm(Double nextRUCDueKm) {
        this.nextRUCDueKm = nextRUCDueKm;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVehicleAssetType() {
        return vehicleAssetType;
    }

    public void setVehicleAssetType(String vehicleAssetType) {
        this.vehicleAssetType = vehicleAssetType;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public String getNextServiceType() {
        return nextServiceType;
    }

    public void setNextServiceType(String nextServiceType) {
        this.nextServiceType = nextServiceType;
    }

    public Double getHubo() {
        return hubo;
    }

    public void setHubo(Double hubo) {
        this.hubo = hubo;
    }

    public String getRucOdoType() {
        return rucOdoType;
    }

    public void setRucOdoType(String rucOdoType) {
        this.rucOdoType = rucOdoType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getNextServiceEstDate() {
        return nextServiceEstDate;
    }

    public void setNextServiceEstDate(Date nextServiceEstDate) {
        this.nextServiceEstDate = nextServiceEstDate;
    }

    public NimbusPlant getAsset() {
        return asset;
    }

    public void setAsset(NimbusPlant asset) {
        this.asset = asset;
    }

    public Date getU_UpdatedDateTime() {
        return u_UpdatedDateTime;
    }

    public void setU_UpdatedDateTime(Date u_UpdatedDateTime) {
        this.u_UpdatedDateTime = u_UpdatedDateTime;
    }

    public Double getNextServiceKM() {
        return nextServiceKM;
    }

    public void setNextServiceKM(Double nextServiceKM) {
        this.nextServiceKM = nextServiceKM;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getU_Employee() {
        return u_Employee;
    }

    public void setU_Employee(String u_Employee) {
        this.u_Employee = u_Employee;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "requestId=" + requestId +
                ", requestEmployee='" + requestEmployee + '\'' +
                ", requestTimestamp=" + requestTimestamp +
                ", dataset='" + dataset + '\'' +
                ", apiTransID=" + apiTransID +
                ", timestamp=" + timestamp +
                ", plantNumber='" + plantNumber + '\'' +
                ", rego='" + rego + '\'' +
                ", site='" + site + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", hours=" + hours +
                ", nextWofDue=" + nextWofDue +
                ", nextRegoDue=" + nextRegoDue +
                ", nextRUCDueDate=" + nextRUCDueDate +
                ", nextRUCDueKm=" + nextRUCDueKm +
                ", groupName='" + groupName + '\'' +
                ", vehicleAssetType='" + vehicleAssetType + '\'' +
                ", odometer=" + odometer +
                ", nextServiceType='" + nextServiceType + '\'' +
                ", hubo=" + hubo +
                ", rucOdoType='" + rucOdoType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", nextServiceEstDate=" + nextServiceEstDate +
                ", AssetItem=" + asset.toString() +
                ", u_UpdatedDateTime=" + u_UpdatedDateTime +
                ", nextServiceKM=" + nextServiceKM +
                ", job=" + job.toString() +
                ", u_Employee='" + u_Employee + '\'' +
                '}';
    }
}
