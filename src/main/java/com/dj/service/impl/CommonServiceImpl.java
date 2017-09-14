package com.dj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dto.User;
import com.dj.dto.Vendor;
import com.dj.service.CommonService;
import com.dj.utils.Constants;
import com.dj.utils.Mailer;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	Mailer mailer;

	@Override
	public void sendPasswordResetMail(String email) {
		
		try {
			User user = userRepository.findByEmail(email);
			if(null != user) {
				 mailer.prepareEmail(user, Constants.FORGOT_PASSWORD, "Reset Password");
			}
			else{
				Vendor vendor = vendorRepository.findByEmail(email);
				if(null != vendor) {
					mailer.prepareEmail(vendor, Constants.FORGOT_PASSWORD, "Reset Password");
				}
				else{
					throw new CustomGenericException("INVALID_EMAIL",HttpStatus.BAD_REQUEST);
				}
			}
			
		}
		catch(Exception e) {
			throw new CustomGenericException("INVALID_EMAIL",HttpStatus.BAD_REQUEST);
		}
		
	}

}
