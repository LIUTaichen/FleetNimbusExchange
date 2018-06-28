package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Resource_Owner")
public class ResourceOwner {

    @Id
    @Column(name="ID", unique=true, nullable=false)
    private Integer id;

    @Column(name="Company")
    private String company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
