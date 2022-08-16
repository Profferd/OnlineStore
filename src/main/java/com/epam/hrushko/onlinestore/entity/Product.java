package com.epam.hrushko.onlinestore.entity;

import java.util.Objects;

/**
 * Product entity
 */
public class Product {
    private int id;
    private int categoryId;
    private int promotionId;
    private String name;
    private String description;
    private double price;
    private boolean status;
    private String photo;
    private int orderNumber;

    public Product() {
        this.promotionId = 0;
    }

    public Product(int id, int categoryId, int promotionId, String name,
                   String description, double price, boolean status, String photo, int orderNumber) {
        this.id = id;
        this.categoryId = categoryId;
        this.promotionId = promotionId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.photo = photo;
        this.orderNumber = orderNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && categoryId == product.categoryId && promotionId == product.promotionId &&
                Double.compare(product.price, price) == 0 && status == product.status &&
                orderNumber == product.orderNumber && name.equals(product.name) &&
                description.equals(product.description) && photo.equals(product.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, promotionId, name, description, price, status, photo, orderNumber);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", promotionId=" + promotionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", photo='" + photo + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
