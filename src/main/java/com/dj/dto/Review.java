package com.dj.dto;

import java.util.Date;

public class Review {


    private int reviewId;
    private int userId;
    private int bookedUserId;
    private String description;
    private double rating;
    private String suggestions;
    private String createdBy;
    private Date createdOn;
    private char isVerifiedRating;
    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getBookedUserId() {
        return bookedUserId;
    }
    public void setBookedUserId(int bookedUserId) {
        this.bookedUserId = bookedUserId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getSuggestions() {
        return suggestions;
    }
    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
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
    public char getIsVerifiedRating() {
        return isVerifiedRating;
    }
    public void setIsVerifiedRating(char isVerifiedRating) {
        this.isVerifiedRating = isVerifiedRating;
    }
    @Override
    public String toString() {
        return "Review [reviewId=" + reviewId + ", userId=" + userId + ", bookedUserId=" + bookedUserId + ", description=" + description + ", rating=" + rating + ", suggestions=" + suggestions + ", createdBy="
                        + createdBy + ", createdOn=" + createdOn + ", isVerifiedRating=" + isVerifiedRating + "]";
    }
    
    

}
