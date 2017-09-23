package com.dj.utils;

public class Constants {
	
	public static final String APPLICATION_PROPERTIES = "classpath:/config/application.properties";
	
	public static final String EMAIL_TO = "email.register.to";
	
	public static final String EMAIL_FROM = "email.register.from";
	
	public static final String EMAIL_SUBJECT = "email.register.subject";
	
	public static final String COMPANY_NAME = "email.register.company";
	
	public static final String DOMAIN_NAME = "email.host";
	
	//Email Template Names
	
	public static final String EMAIL_VERIFICATION = "velocity_mailTemplate.vm";
	
	public static final String FORGOT_PASSWORD = "velocity_forgotPassword.vm";
	
	
	//ROLES
	public static final String ROLE_USER = "ROLE_USER";
	
	public static final String ROLE_VENDOR = "ROLE_VENDOR";
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	
	

	
	
	
	
	
	
	//AWS keys 
	
	public enum MediaType {
		AUDIO("audio", "/audio/"),
		VIDEO("video", "/video/"),
		IMAGE("image", "/image/"),
		OTHER("other", "/other");

	    private final String key;
	    private final String value;

	    MediaType(String key, String value) {
	        this.key = key;
	        this.value = value;
	    }

	    public String getKey() {
	        return key;
	    }
	    public String getValue() {
	        return value;
	    }
	}
	
	
	
}
