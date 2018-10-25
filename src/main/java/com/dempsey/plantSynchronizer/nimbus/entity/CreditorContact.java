package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Contacts")
public class CreditorContact {

    @Id
    @Column(name="ContactID", unique=true, nullable=false)
    private Integer id;

    @Column(name="Name")
    private String company;

    @Column(name = "phone")
    private String phone;

    @Column(name = "WebAddress")
    private String webPage;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }


    @Override
    public String toString() {
        return "CreditorContact{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", phone='" + phone + '\'' +
                ", webPage='" + webPage + '\'' +
                '}';
    }
}
