package com.dj.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.LocationsRepository;
import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dto.Locations;
import com.dj.dto.User;
import com.dj.dto.Vendor;
import com.dj.model.LocationsDto;
import com.dj.service.CommonService;
import com.dj.utils.Constants;
import com.dj.utils.EncryptionUtils;
import com.dj.utils.Mailer;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	LocationsRepository locationsRepository;

	@Override
	public void sendPasswordResetMail(String email) throws CustomGenericException{
		
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

	@Override
	public void resetPassword(Map<String, String> map) {
		if(StringUtils.isNotEmpty(map.get("type")) && map.get("type").equals("user")) {
			User user = userRepository.findByUserId(Long.valueOf(map.get("id")));
			String password = EncryptionUtils.passwordEncoder(user.getEmail(), map.get("password"));
			user.setPassword(password);
			userRepository.save(user);
		}
		else if(map.get("type").equals("vendor")) {
			Vendor vendor = vendorRepository.findByVendorId(Long.valueOf(map.get("id")));
			String password = EncryptionUtils.passwordEncoder(vendor.getEmail(), map.get("password"));
			vendor.setPassword(password);
			vendorRepository.save(vendor);
		}
		else{
			throw new CustomGenericException("INVALID_USER",HttpStatus.BAD_REQUEST); 
		}
	}

	@Override
	public boolean verifyEmail(String email) {
		boolean isAlreadyExists = false;
		if(vendorRepository.countByEmail(email) > 0 || userRepository.countByEmail(email) > 0){
			isAlreadyExists =true;
		}
		
		return isAlreadyExists;
	}

	@Override
	public LocationsDto fetchLocations(String pinCode) {

		List<Locations> list = locationsRepository.findByPinCode(pinCode);
		LocationsDto dto =  new LocationsDto();
		Set<String> localities = new HashSet<>();
		if(!CollectionUtils.isEmpty(list)) {
			dto.setState(list.get(0).getState().getName());
			dto.setDistrict(list.get(0).getDistrict());
		}
		for(Locations locations : list) {
			localities.add(locations.getFirstLocality());
			localities.add(locations.getSecondLocality());
			localities.add(locations.getThirdLocality());
			localities.add(locations.getSubDistrict());
			localities.add(locations.getVillage());
		}
		dto.setLocalities(localities);
		return dto;
	}

}
