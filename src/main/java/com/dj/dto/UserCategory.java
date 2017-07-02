package com.dj.dto;

import java.util.Date;

public class UserCategory {

    private int userCategoryId;
    private int userId;
    private int categoryId;
    private int status;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    
    
    public int getUserCategoryId() {
        return userCategoryId;
    }
    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    @Override
    public String toString() {
        return "UserCategory [userCategoryId=" + userCategoryId + ", userId=" + userId + ", categoryId=" + categoryId + ", status=" + status + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", modifiedBy="
                        + modifiedBy + ", modifiedOn=" + modifiedOn + "]";
    }


}
