package com.yun.security.po;

import java.io.Serializable;
import java.util.Date;

public class RepicUserManufacturerRel implements Serializable {
    private String id;

    private String repicManufacturerId;

    private String ucUserId;

    private Integer status;

    private String addId;

    private Date addTime;

    private String updateId;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRepicManufacturerId() {
        return repicManufacturerId;
    }

    public void setRepicManufacturerId(String repicManufacturerId) {
        this.repicManufacturerId = repicManufacturerId == null ? null : repicManufacturerId.trim();
    }

    public String getUcUserId() {
        return ucUserId;
    }

    public void setUcUserId(String ucUserId) {
        this.ucUserId = ucUserId == null ? null : ucUserId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId == null ? null : addId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}