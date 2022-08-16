package com.epam.hrushko.onlinestore.entity;

import java.util.Date;
import java.util.Objects;

public class Promotion {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String photo;
    private byte discount;

    public Promotion(){}

    public Promotion(int id, String name, String description, Date startDate, Date endDate, String photo, byte discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photo = photo;
        this.discount = discount;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return id == promotion.id && discount == promotion.discount && name.equals(promotion.name) &&
                description.equals(promotion.description) && startDate.equals(promotion.startDate) &&
                endDate.equals(promotion.endDate) && photo.equals(promotion.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startDate, endDate, photo, discount);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", photo='" + photo + '\'' +
                ", discount=" + discount +
                '}';
    }
}

