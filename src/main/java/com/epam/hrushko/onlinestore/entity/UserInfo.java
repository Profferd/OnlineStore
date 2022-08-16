package com.epam.hrushko.onlinestore.entity;

import java.util.Objects;

public class UserInfo {
    private int id;
    private String name;
    private String surname;
    private long phone;

    public UserInfo(){
    }

    public UserInfo(int id, String name, String surname, long phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id && phone == userInfo.phone && name.equals(userInfo.name) && surname.equals(userInfo.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                '}';
    }
}
