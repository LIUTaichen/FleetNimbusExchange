package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="u_assetsubgroup")
public class AssetSubGroup {

    @Id
    @Column(name="assetsubgroup", unique=true, nullable=false)
    private String assetSubGroup;

    @Column(name="description")
    private String description;

    @Column(name="detailed_description")
    private String detailedDescription;

    @Column(name="discontinued")
    private Boolean discontinued;

    //@Column(name="u_isbillable")
    @Transient
    private Boolean isBillable;


    public String getAssetSubGroup() {
        return assetSubGroup;
    }

    public void setAssetSubGroup(String assetSubGroup) {
        this.assetSubGroup = assetSubGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "AssetSubGroup{" +
                "assetSubGroup='" + assetSubGroup + '\'' +
                ", description='" + description + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", discontinued=" + discontinued +
                ", isBillable=" + isBillable +
                '}';
    }

    public Boolean getBillable() {
        return isBillable;
    }

    public void setBillable(Boolean billable) {
        isBillable = billable;
    }

}
