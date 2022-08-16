package com.epam.hrushko.onlinestore.entity;

/**
 * User entity
 */
public class User {
    private int id;
    private int userInfoId;
    private String email;
    private String password;
    private int roleId;

    public User() {
    }

    public User(int id, int userInfoId, int roleId, String email, String password) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return id == user.id &&
                userInfoId == user.userInfoId &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                roleId == user.roleId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User:{");
        sb.append("id = ").append(id)
                .append(", userInfoId = ").append(userInfoId).append("\r\n")
                .append("email = ").append(email)
                .append(", password = ").append(password).append("\r\n")
                .append("roleId = ").append(roleId).append('}');
        return sb.toString();
    }
}
