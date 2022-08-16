package com.epam.hrushko.onlinestore.entity;

import java.util.Objects;

/**
 * Order entity
 */
public class Order {
    private int id;
    private int productId;
    private int userId;
    private int number;
    private int orderId;

    public Order(){}

    public Order(int id, int productId, int userId, int orderId, int number) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.orderId = orderId;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && productId == order.productId && userId == order.userId && number == order.number && orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, number, orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", number=" + number +
                ", orderId=" + orderId +
                '}';
    }
}
