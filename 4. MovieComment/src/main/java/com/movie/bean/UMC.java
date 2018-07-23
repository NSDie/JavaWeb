package com.movie.bean;

import java.util.Date;

public class UMC {
    private Integer id;

    private Integer uid;

    private Integer mid;

    private Integer cid;

    private Comment comment;

    private User user;

    public User getUser(){return user;}

    public void setUser(User user){this.user=user;}

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}