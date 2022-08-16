package com.epam.hrushko.onlinestore.entity;

import java.util.Date;
import java.util.Objects;

public class UserOrder {
    private int id;
    private String address;
    private Date orderDate;
    private Date deliveryDate;
    private String status;

    public UserOrder() {}

    public UserOrder(int id, String address, Date orderDate, Date deliveryDate, String status) {
        this.id = id;
        this.address = address;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id && address.equals(userOrder.address) && orderDate.equals(userOrder.orderDate) && deliveryDate.equals(userOrder.deliveryDate) && status.equals(userOrder.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, orderDate, deliveryDate, status);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                '}';
    }
}
