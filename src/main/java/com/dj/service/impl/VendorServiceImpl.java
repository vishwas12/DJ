package com.dj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.AuthUserRepository;
import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dao.VendorVerificationRepository;
import com.dj.dto.AuthUser;
import com.dj.dto.Vendor;
import com.dj.dto.VendorVerification;
import com.dj.security.CustomAuthenticationProvider;
import com.dj.service.VendorService;
import com.dj.utils.EncryptionUtils;
import com.dj.utils.Mailer;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	AuthUserRepository authUserRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Mailer mailer;
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	VendorVerificationRepository vendorVerificationRepository;
	
	@Override
	public void vendorSignUp(Vendor vendor) {
 
		if(vendorRepository.countByEmail(vendor.getEmail()) < 1){
			if(userRepository.countByEmail(vendor.getEmail()) < 1) {
				vendor.setPassword(EncryptionUtils.passwordEncoder(vendor.getEmail(),vendor.getPassword()));
				vendor.setCreatedOn(new Date());
				vendor = vendorRepository.save(vendor);
				insertAuthUser(vendor);
				mailer.prepareEmail(vendor);
				autoLoginUser(vendor);
			}
			else {
				throw new CustomGenericException("REGISTERED_AS_NORMAL", HttpStatus.BAD_REQUEST);
			}
		}else{
			throw new CustomGenericException("VENDOR_ALREADY_REGISTERED", HttpStatus.BAD_REQUEST);
		}
	
	}
	
	private void insertAuthUser(Vendor vendor){
		AuthUser authUser = new AuthUser();
		authUser.setEmail(vendor.getEmail());
		authUser.setFirstName(vendor.getFirstName());
		authUser.setLastName(vendor.getLastName());
		authUser.setPassword(vendor.getPassword());
		authUserRepository.save(authUser);
	}
	
	private void autoLoginUser(Vendor vendor){
		try{
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
	    	//fetch user roles from database
	    	List<String> roles = new ArrayList<String>();
	    	roles.add("ROLE_USER");
	        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	        //vendor.setRoles(roles);
	        Authentication auth = new UsernamePasswordAuthenticationToken(vendor, vendor.getPassword(), grantedAuths);
	        SecurityContextHolder.getContext().setAuthentication(auth);
		}catch (AuthenticationException e) {
			// TODO: handle exception
			SecurityContextHolder.getContext().setAuthentication(null);
            e.printStackTrace();
		}
	}

	@Override
	public boolean verifyEmail(Long id, String code) {
		VendorVerification vvf = vendorVerificationRepository.findByVendorId(id);
		boolean isVerified = false;
		if(null != vvf) {
			if(null != vvf.getVerificationCode() && vvf.getVerificationCode().equals(code)) {
				isVerified =true;
				vendorRepository.updateEmailVerificationStatus(id);
			}
			else {
				throw new CustomGenericException("EMAIL_VERIFICATION_TOKEN_EXPIRED",HttpStatus.OK);
			}
		}
		else{
			throw new CustomGenericException("INVALID_EMAIL_VERIFICATION",HttpStatus.OK);
		}
		return isVerified;
	}

}
