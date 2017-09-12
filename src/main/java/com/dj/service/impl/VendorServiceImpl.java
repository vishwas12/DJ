package com.dj.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dto.Vendor;
import com.dj.service.VendorService;
import com.dj.utils.EncryptionUtils;
import com.dj.utils.Mailer;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Mailer mailer;
	
	@Override
	public void vendorSignUp(Vendor vendor) {
 
		if(vendorRepository.countByEmail(vendor.getEmail()) < 1){
			if(userRepository.countByEmail(vendor.getEmail()) < 1) {
				vendor.setPassword(EncryptionUtils.passwordEncoder(vendor.getEmail(),vendor.getPassword()));
				vendor.setCreatedOn(new Date());
				vendor = vendorRepository.save(vendor);
				mailer.prepareEmail(vendor);
			}
			else {
				throw new CustomGenericException("REGISTERED_AS_NORMAL", HttpStatus.BAD_REQUEST);
			}
		}else{
			throw new CustomGenericException("VENDOR_ALREADY_REGISTERED", HttpStatus.BAD_REQUEST);
		}
	
	}

}
