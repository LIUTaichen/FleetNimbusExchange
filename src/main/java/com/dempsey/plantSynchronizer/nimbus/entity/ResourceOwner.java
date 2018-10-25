package com.dempsey.plantSynchronizer.nimbus.entity;

import javax.persistence.*;

@Entity
@Table(name="Creditors")
public class ResourceOwner {

    @Id
    @Column(name="CreditorID", unique=true, nullable=false)
    private Integer id;

    @Column(name="CreditorIndex")
    private String creditorIndex;

    @OneToOne
    @JoinColumn(name="ContactID")
    private CreditorContact contact;

    @Column(name = "notes")
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreditorIndex() {
        return creditorIndex;
    }

    public void setCreditorIndex(String creditorIndex) {
        this.creditorIndex = creditorIndex;
    }

    public CreditorContact getContact() {
        return contact;
    }

    public void setContact(CreditorContact contact) {
        this.contact = contact;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ResourceOwner{" +
                "id=" + id +
                ", creditorIndex='" + creditorIndex + '\'' +
                ", contact=" + contact +
                ", notes='" + notes + '\'' +
                '}';
    }
}
