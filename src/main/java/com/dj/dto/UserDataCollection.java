package com.dj.dto;

import java.util.Date;

public class UserDataCollection {

    private int userMusicCollectionId;
    private int userId;
    private String dataLink;
    private int categoryId;
    private int status;
    private String createdBy;
    private Date createdOn;
    public String getDataLink() {
        return dataLink;
    }
    public void setDataLink(String dataLink) {
        this.dataLink = dataLink;
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
    public int getUserMusicCollectionId() {
        return userMusicCollectionId;
    }
    public void setUserMusicCollectionId(int userMusicCollectionId) {
        this.userMusicCollectionId = userMusicCollectionId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "UserMusicCollection [userMusicCollectionId=" + userMusicCollectionId + ", userId=" + userId + ", dataLink=" + dataLink + ", categoryId=" + categoryId + ", status=" + status + ", createdBy=" + createdBy
                        + ", createdOn=" + createdOn + "]";
    }
    

}
