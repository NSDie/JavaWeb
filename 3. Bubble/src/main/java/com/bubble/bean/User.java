package com.bubble.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {

    private Integer id;

    private String username;
    @JsonIgnore
    private String password;

    private String phone;

    private String image;

    private Integer emojicount;

    private Date logintime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getEmojicount() {
        return emojicount;
    }

    public void setEmojicount(Integer emojicount) {
        this.emojicount = emojicount;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

}