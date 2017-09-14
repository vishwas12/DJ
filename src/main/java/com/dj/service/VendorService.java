package com.dj.service;

import java.util.Map;

import com.dj.dto.Vendor;

public interface VendorService {

	void vendorSignUp(Vendor vendor);

	boolean verifyEmail(Long id, String code);

	void sendPasswordResetMail(Map<String, String> map);

}
