package com.bubble.bean;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer pid;

    private Integer uid;

    private String content;

    private Date time;

    private MiniUser miniUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public MiniUser getMiniUser() {
        return miniUser;
    }

    public void setMiniUser(MiniUser miniUser) {
        this.miniUser = miniUser;
    }
}