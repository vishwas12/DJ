package com.dj.dto;

public class UserMusicCollection {
	private int userMusicCollectionId;
	private int userId;
	private String musicLink;
	private int musicType;
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
	public String getMusicLink() {
		return musicLink;
	}
	public void setMusicLink(String musicLink) {
		this.musicLink = musicLink;
	}
	public int getMusicType() {
		return musicType;
	}
	public void setMusicType(int musicType) {
		this.musicType = musicType;
	}
	
}
