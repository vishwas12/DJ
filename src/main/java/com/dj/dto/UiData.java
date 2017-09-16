package com.dj.dto;

public class UiData {

	private long id;
	private boolean isSuccess;
	private Object data;
	private String message;
	
	
	public UiData(boolean isSuccess, String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public UiData(boolean isSuccess, Object data, String message) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
		this.message = message;
	}
	

	public UiData() {
		super();
	}

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

	@Override
	public String toString() {
		return "UiData [id=" + id + ", isSuccess=" + isSuccess + ", data=" + data + ", message=" + message + "]";
	}
	
	

}
