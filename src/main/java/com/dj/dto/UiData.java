package com.dj.dto;

public class UiData {

	private long id;
	private boolean isSuccess;
	private Object data;
	private String token; //tobe removed
	private String screenUrl;
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getScreenUrl() {
		return screenUrl;
	}

	public void setScreenUrl(String screenUrl) {
		this.screenUrl = screenUrl;
	}

}
