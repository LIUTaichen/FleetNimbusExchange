package com.dempsey.plantSynchronizer.nimbus.entity;

import com.dempsey.plantSynchronizer.fleet.domain.enumeration.HireStatus;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the Plant database table.
 *
 */
@Entity
@Table(name="assetitems")
public class NimbusPlant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="assetitemid", unique=true, nullable=false)
    private Integer id;

    @Column(name="u_wofduedate")
    private Date cert_Due;

    @Column(name="description")
    private String description;

    @Column(name="u_plantid")
    private String fleetId;

    @Column(name="hubboreading", precision=28, scale=6)
    private Integer hub_Reading;

    @Column(name="currentreading", precision=28, scale=6)
    private Integer last_Log;

    @Column(name="datecurrentreading")
    private Date last_Log_Date;

    @Column(name="u_projectid")
    private String last_Log_Project;

    @Column(name="u_nextservicekm")
    private Integer Maintenance_Due;

    @Column(name="u_regonumber")
    private String registration;

    @Column(name="u_regoduedate")
    private Date registration_Due_Date;

    @Column(name="u_rucdueatkm",precision=28, scale=6)
    private Integer RUC_Due;

    @Column(name="u_kmsuntilnextservice", precision=28, scale=6)
    private Integer units_Til_Maintenance;

    @ManyToOne
    @JoinColumn(name="u_assetsubgroup")
    private AssetSubGroup assetSubGroup;

    @Column(name ="u_deviceserialno")
    private String deviceSerialNo;

    @Column(name="u_metertype")
    private String meterType;

    @Column(name="itemnotes")
    private String notes;

    @Column(name="u_hiredfrom")
    private Integer ownerId;

    @Column(name="u_vinnumber")
    private String vinNumber;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "u_hirestatus")
    @Transient
    private HireStatus hireStatus;

    @Column(name="closed")
    private Boolean closed;


    @OneToMany(mappedBy = "asset")
    private Set<ApiRequest> requests = new HashSet<ApiRequest>();


    public NimbusPlant() {
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

    public Date getCert_Due() {
        return cert_Due;
    }

    public void setCert_Due(Date cert_Due) {
        this.cert_Due = cert_Due;
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

    public Integer getHub_Reading() {
        return hub_Reading;
    }

    public void setHub_Reading(Integer hub_Reading) {
        this.hub_Reading = hub_Reading;
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

    public String getLast_Log_Project() {
        return last_Log_Project;
    }

    public void setLast_Log_Project(String last_Log_Project) {
        this.last_Log_Project = last_Log_Project;
    }

    public Integer getMaintenance_Due() {
        return Maintenance_Due;
    }

    public void setMaintenance_Due(Integer maintenance_Due) {
        Maintenance_Due = maintenance_Due;
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

    public Integer getUnits_Til_Maintenance() {
        return units_Til_Maintenance;
    }

    public void setUnits_Til_Maintenance(Integer units_Til_Maintenance) {
        this.units_Til_Maintenance = units_Til_Maintenance;
    }

    public AssetSubGroup getAssetSubGroup() {
        return assetSubGroup;
    }

    public void setAssetSubGroup(AssetSubGroup assetSubGroup) {
        this.assetSubGroup = assetSubGroup;
    }


    public String getDeviceSerialNo() {
        return deviceSerialNo;
    }

    public void setDeviceSerialNo(String deviceSerialNo) {
        this.deviceSerialNo = deviceSerialNo;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }



    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "NimbusPlant{" +
                "id=" + id +
                ", cert_Due=" + cert_Due +
                ", description='" + description + '\'' +
                ", fleetId='" + fleetId + '\'' +
                ", hub_Reading=" + hub_Reading +
                ", last_Log=" + last_Log +
                ", last_Log_Date=" + last_Log_Date +
                ", last_Log_Project='" + last_Log_Project + '\'' +
                ", Maintenance_Due=" + Maintenance_Due +
                ", registration='" + registration + '\'' +
                ", registration_Due_Date=" + registration_Due_Date +
                ", RUC_Due=" + RUC_Due +
                ", units_Til_Maintenance=" + units_Til_Maintenance +
                ", assetSubGroup=" + assetSubGroup +
                ", deviceSerialNo='" + deviceSerialNo + '\'' +
                ", meterType='" + meterType + '\'' +
                ", notes='" + notes + '\'' +
                ", ownerId=" + ownerId +
                ", vinNumber='" + vinNumber + '\'' +
                ", hireStatus=" + hireStatus +
                ", closed=" + closed +
                '}';
    }

    public HireStatus getHireStatus() {
        return hireStatus;
    }

    public void setHireStatus(HireStatus hireStatus) {
        this.hireStatus = hireStatus;
    }

    public Set<ApiRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<ApiRequest> requests) {
        this.requests = requests;
    }

}