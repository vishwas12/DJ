package com.dj.service;

import com.dj.dto.Vendor;

public interface VendorService {

	void vendorSignUp(Vendor vendor);

	boolean verifyEmail(Long id, String code);

	boolean verifyPasswordLink(Long id, String code);

}
