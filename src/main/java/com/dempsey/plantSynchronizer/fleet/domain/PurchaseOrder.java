package com.dempsey.plantSynchronizer.fleet.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public PurchaseOrder orderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
        PurchaseOrder purchaseOrder = (PurchaseOrder) o;
        if (purchaseOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
            "id=" + getId() +
            ", orderNumber='" + getOrderNumber() + "'" +
            "}";
    }
}
