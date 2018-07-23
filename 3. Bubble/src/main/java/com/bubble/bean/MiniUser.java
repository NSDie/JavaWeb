package com.bubble.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class MiniUser {

    private String username;

    private String phone;

    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getImage() {
        return avatar;
    }

    public void setImage(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

}
