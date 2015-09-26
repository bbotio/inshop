package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This class represents available delivery type for the shop.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class ShopDelivery implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @OneToOne
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id")
    private DeliveryType deliveryTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DeliveryType getDeliveryType() {
        return deliveryTypes;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryTypes = deliveryType;
    }

    @Override
    public String toString() {
        return "ShopDelivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", deliveryTypes=" + deliveryTypes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopDelivery that = (ShopDelivery) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return !(deliveryTypes != null ? !deliveryTypes.equals(that.deliveryTypes) : that.deliveryTypes != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (deliveryTypes != null ? deliveryTypes.hashCode() : 0);
        return result;
    }
}
