package com.chang.entity;

import java.util.Date;

public class MemoGroup {
    private Integer id;
    private String name;
    private Date createdTime;
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCratedDate(Date date) {
        return createdTime;
    }

    public void setCratedDate(Date cratedDate) {
        this.createdTime = cratedDate;
    }

    public Date getModifyDate() {
        return modifyTime;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyTime = modifyDate;
    }

    @Override
    public String toString() {
        return "memoGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
