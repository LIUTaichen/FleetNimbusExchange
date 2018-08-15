package com.dempsey.plantSynchronizer.fleet.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.dempsey.plantSynchronizer.fleet.domain.enumeration.MaintenanceType;
import com.dempsey.plantSynchronizer.fleet.domain.enumeration.MeterUnit;

import com.dempsey.plantSynchronizer.fleet.domain.enumeration.HireStatus;

/**
 * A Plant.
 */
@Entity
@Table(name = "plant")
public class Plant extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fleet_id")
    private String fleetId;

    @Column(name = "name")
    private String name;

    @Column(name = "notes")
    private String notes;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "vin")
    private String vin;

    @Column(name = "rego")
    private String rego;

    @Column(name = "date_of_manufacture")
    private Instant dateOfManufacture;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "tank_size")
    private Double tankSize;

    @Column(name = "meter_reading")
    private Double meterReading;

    @Column(name = "maintenance_due_at")
    private Double maintenanceDueAt;

    @Column(name = "maintenance_due_date")
    private Instant maintenanceDueDate;

    @Column(name = "last_maintenance_date")
    private Instant lastMaintenanceDate;

    @Column(name = "last_maintenance_at")
    private Double lastMaintenanceAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "meter_unit")
    private MeterUnit meterUnit;

    @Column(name = "certificate_due_date")
    private Instant certificateDueDate;

    @Column(name = "ruc_due_at_km")
    private Double rucDueAtKm;

    @Column(name = "hubbo_reading")
    private Double hubboReading;

    @Column(name = "load_capacity")
    private Double loadCapacity;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "registration_due_date")
    private Instant registrationDueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "hire_status")
    private HireStatus hireStatus;

    @Column(name = "gps_device_serial")
    private String gpsDeviceSerial;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_type")
    private MaintenanceType maintenanceType;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Company owner;

    @ManyToOne
    @JoinColumn(name = "assigned_contractor_id")
    private MaintenanceContractor assignedContractor;

    @ManyToOne
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFleetId() {
        return fleetId;
    }

    public Plant fleetId(String fleetId) {
        this.fleetId = fleetId;
        return this;
    }

    public void setFleetId(String fleetId) {
        this.fleetId = fleetId;
    }

    public String getName() {
        return name;
    }

    public Plant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public Plant notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public Plant purchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public void setPurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Plant isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public Plant description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVin() {
        return vin;
    }

    public Plant vin(String vin) {
        this.vin = vin;
        return this;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRego() {
        return rego;
    }

    public Plant rego(String rego) {
        this.rego = rego;
        return this;
    }

    public void setRego(String rego) {
        this.rego = rego;
    }

    public Instant getDateOfManufacture() {
        return dateOfManufacture;
    }

    public Plant dateOfManufacture(Instant dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
        return this;
    }

    public void setDateOfManufacture(Instant dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public byte[] getImage() {
        return image;
    }

    public Plant image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Plant imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Double getTankSize() {
        return tankSize;
    }

    public Plant tankSize(Double tankSize) {
        this.tankSize = tankSize;
        return this;
    }

    public void setTankSize(Double tankSize) {
        this.tankSize = tankSize;
    }

    public Double getMeterReading() {
        return meterReading;
    }

    public Plant meterReading(Double meterReading) {
        this.meterReading = meterReading;
        return this;
    }

    public void setMeterReading(Double meterReading) {
        this.meterReading = meterReading;
    }

    public Double getMaintenanceDueAt() {
        return maintenanceDueAt;
    }

    public Plant maintenanceDueAt(Double maintenanceDueAt) {
        this.maintenanceDueAt = maintenanceDueAt;
        return this;
    }

    public void setMaintenanceDueAt(Double maintenanceDueAt) {
        this.maintenanceDueAt = maintenanceDueAt;
    }

    public Instant getMaintenanceDueDate() {
        return maintenanceDueDate;
    }

    public Plant maintenanceDueDate(Instant maintenanceDueDate) {
        this.maintenanceDueDate = maintenanceDueDate;
        return this;
    }

    public void setMaintenanceDueDate(Instant maintenanceDueDate) {
        this.maintenanceDueDate = maintenanceDueDate;
    }

    public Instant getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public Plant lastMaintenanceDate(Instant lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
        return this;
    }

    public void setLastMaintenanceDate(Instant lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public Double getLastMaintenanceAt() {
        return lastMaintenanceAt;
    }

    public Plant lastMaintenanceAt(Double lastMaintenanceAt) {
        this.lastMaintenanceAt = lastMaintenanceAt;
        return this;
    }

    public void setLastMaintenanceAt(Double lastMaintenanceAt) {
        this.lastMaintenanceAt = lastMaintenanceAt;
    }

    public MeterUnit getMeterUnit() {
        return meterUnit;
    }

    public Plant meterUnit(MeterUnit meterUnit) {
        this.meterUnit = meterUnit;
        return this;
    }

    public void setMeterUnit(MeterUnit meterUnit) {
        this.meterUnit = meterUnit;
    }

    public Instant getCertificateDueDate() {
        return certificateDueDate;
    }

    public Plant certificateDueDate(Instant certificateDueDate) {
        this.certificateDueDate = certificateDueDate;
        return this;
    }

    public void setCertificateDueDate(Instant certificateDueDate) {
        this.certificateDueDate = certificateDueDate;
    }

    public Double getRucDueAtKm() {
        return rucDueAtKm;
    }

    public Plant rucDueAtKm(Double rucDueAtKm) {
        this.rucDueAtKm = rucDueAtKm;
        return this;
    }

    public void setRucDueAtKm(Double rucDueAtKm) {
        this.rucDueAtKm = rucDueAtKm;
    }

    public Double getHubboReading() {
        return hubboReading;
    }

    public Plant hubboReading(Double hubboReading) {
        this.hubboReading = hubboReading;
        return this;
    }

    public void setHubboReading(Double hubboReading) {
        this.hubboReading = hubboReading;
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public Plant loadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
        return this;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public Plant hourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Instant getRegistrationDueDate() {
        return registrationDueDate;
    }

    public Plant registrationDueDate(Instant registrationDueDate) {
        this.registrationDueDate = registrationDueDate;
        return this;
    }

    public void setRegistrationDueDate(Instant registrationDueDate) {
        this.registrationDueDate = registrationDueDate;
    }

    public HireStatus getHireStatus() {
        return hireStatus;
    }

    public Plant hireStatus(HireStatus hireStatus) {
        this.hireStatus = hireStatus;
        return this;
    }

    public void setHireStatus(HireStatus hireStatus) {
        this.hireStatus = hireStatus;
    }

    public String getGpsDeviceSerial() {
        return gpsDeviceSerial;
    }

    public Plant gpsDeviceSerial(String gpsDeviceSerial) {
        this.gpsDeviceSerial = gpsDeviceSerial;
        return this;
    }

    public void setGpsDeviceSerial(String gpsDeviceSerial) {
        this.gpsDeviceSerial = gpsDeviceSerial;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public Plant maintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
        return this;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Location getLocation() {
        return location;
    }

    public Plant location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public Plant category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getOwner() {
        return owner;
    }

    public Plant owner(Company company) {
        this.owner = company;
        return this;
    }

    public void setOwner(Company company) {
        this.owner = company;
    }

    public MaintenanceContractor getAssignedContractor() {
        return assignedContractor;
    }

    public Plant assignedContractor(MaintenanceContractor maintenanceContractor) {
        this.assignedContractor = maintenanceContractor;
        return this;
    }

    public void setAssignedContractor(MaintenanceContractor maintenanceContractor) {
        this.assignedContractor = maintenanceContractor;
    }

    public Project getProject() {
        return project;
    }

    public Plant project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plant plant = (Plant) o;
        if (plant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + getId() +
                ", fleetId='" + getFleetId() + "'" +
                ", name='" + getName() + "'" +
                ", notes='" + getNotes() + "'" +
                ", purchaseDate='" + getPurchaseDate() + "'" +
                ", isActive='" + isIsActive() + "'" +
                ", description='" + getDescription() + "'" +
                ", vin='" + getVin() + "'" +
                ", rego='" + getRego() + "'" +
                ", dateOfManufacture='" + getDateOfManufacture() + "'" +
                ", image='" + getImage() + "'" +
                ", imageContentType='" + getImageContentType() + "'" +
                ", tankSize=" + getTankSize() +
                ", meterReading=" + getMeterReading() +
                ", maintenanceDueAt=" + getMaintenanceDueAt() +
                ", maintenanceDueDate='" + getMaintenanceDueDate() + "'" +
                ", lastMaintenanceDate='" + getLastMaintenanceDate() + "'" +
                ", lastMaintenanceAt=" + getLastMaintenanceAt() +
                ", meterUnit='" + getMeterUnit() + "'" +
                ", certificateDueDate='" + getCertificateDueDate() + "'" +
                ", rucDueAtKm=" + getRucDueAtKm() +
                ", hubboReading=" + getHubboReading() +
                ", loadCapacity=" + getLoadCapacity() +
                ", hourlyRate=" + getHourlyRate() +
                ", registrationDueDate='" + getRegistrationDueDate() + "'" +
                ", hireStatus='" + getHireStatus() + "'" +
                ", gpsDeviceSerial='" + getGpsDeviceSerial() + "'" +
                ", maintenanceType='" + getMaintenanceType() + "'" +
                "}";
    }
}
