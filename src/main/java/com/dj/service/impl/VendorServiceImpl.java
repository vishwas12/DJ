package com.dj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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
import org.springframework.util.CollectionUtils;

import com.dj.application.exception.CustomGenericException;
import com.dj.dao.AuthUserRepository;
import com.dj.dao.CategoryRepository;
import com.dj.dao.EquipmentRepository;
import com.dj.dao.RoleRepository;
import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dao.VendorVerificationRepository;
import com.dj.dto.AuthUser;
import com.dj.dto.Category;
import com.dj.dto.Equipments;
import com.dj.dto.Role;
import com.dj.dto.Vendor;
import com.dj.dto.VendorVerification;
import com.dj.model.EquipmentDto;
import com.dj.model.VendorDto;
import com.dj.security.CustomAuthenticationProvider;
import com.dj.service.VendorService;
import com.dj.utils.Constants;
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
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Override
	@Transactional(transactionManager ="transactionManager")
	public void vendorSignUp(Vendor vendor) {
 
		if(vendorRepository.countByEmail(vendor.getEmail()) < 1){
			if(userRepository.countByEmail(vendor.getEmail()) < 1) {
				vendor.setPassword(EncryptionUtils.passwordEncoder(vendor.getEmail(),vendor.getPassword()));
				
				Role role = roleRepository.findByRole(Constants.ROLE_VENDOR);
				List<Role> roles =  new ArrayList<>();
				roles.add(role);
				vendor.setRoles(roles);
				Category category = categoryRepository.findOne(Integer.toUnsignedLong(vendor.getCategoryId()));
				vendor.setCategory(category);
				vendor = vendorRepository.save(vendor);
				insertAuthUser(vendor);
				mailer.prepareEmail(vendor,Constants.EMAIL_VERIFICATION, "Account Verifications");
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
		authUser.setUserId(vendor.getVendorId());
		List<String> roles = new ArrayList<>();
		for(Role role : vendor.getRoles()) {
			roles.add(role.getRole());
		}
		authUser.setRoles(StringUtils.join(roles, ","));
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

	@Override
	public boolean verifyPasswordLink(Long id, String code) {
		VendorVerification vvf = vendorVerificationRepository.findByVendorId(id);
		boolean isVerified = false;
		if(null != vvf) {
			if(null != vvf.getResetPasswordCode() && vvf.getResetPasswordCode().equals(code)) {
				isVerified =true;
			}
			else {
				throw new CustomGenericException("RESET_PASSWORD_TOKEN_EXPIRED",HttpStatus.OK);
			}
		}
		else{
			throw new CustomGenericException("RESET_PASSWORD_TOKEN_EXPIRED",HttpStatus.OK);
		}
		return isVerified;
	}

	@Override
	public VendorDto fetchDetails(Long id) {
		Vendor vendor = vendorRepository.findVendorWithCategory(id);
		
		VendorDto dto = new VendorDto();
		BeanUtils.copyProperties(vendor, dto,"status");
		dto.setCategory(vendor.getCategory().getName());
		dto.setCategoryId(vendor.getCategory().getCategoryId());
		if(!CollectionUtils.isEmpty(vendor.getCategory().getEquipments())) {
			dto.setEquipmentsExist(true);
		}
		
		return dto;
	}

	@Override
	public List<EquipmentDto> fetchEquipments(Long categoryId) {
		List<Equipments> equipmentsList = equipmentRepository.findByCategoryId(categoryId);
		List<EquipmentDto> equipmentDtos = new ArrayList<>();
		for(Equipments equipments : equipmentsList) {
			EquipmentDto dto =  new EquipmentDto();
			BeanUtils.copyProperties(equipments, dto);
			equipmentDtos.add(dto);
		}
		return equipmentDtos;
	}

}
