package com.dj.utils;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dj.dao.VendorRepository;
import com.dj.dao.VendorVerificationRepository;
import com.dj.dto.Vendor;
import com.dj.dto.VendorVerification;

@Service
@PropertySource(Constants.APPLICATION_PROPERTIES)
public class VendorMailerDBService {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	Environment env;

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	VendorVerificationRepository vendorVerificationRepository;

	public String handleDbOperations(Vendor vendor, String templateName) {
		if (templateName.equals(Constants.EMAIL_VERIFICATION)) {

			VendorVerification vendorVerification = vendorVerificationRepository.findByVendorId(vendor.getVendorId());
			if (null == vendorVerification) {
				vendorVerification = new VendorVerification();
			}

			String verificationCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "?purpose=verifyemail&code=" + verificationCode
					+ "&type=vendor&vendorId=" + vendor.getVendorId();
			vendorVerification.setVerificationCode(verificationCode);
			vendorVerification.setVendor(vendor);
			vendorVerification.setVerificationCodeCreatedDate(new Timestamp(System.currentTimeMillis()));
			vendor.setVendorVerification(vendorVerification);
			vendorRepository.save(vendor);
			return link;
		} else if (templateName.equals(Constants.FORGOT_PASSWORD)) {
			VendorVerification vendorVerification = vendorVerificationRepository.findByVendorId(vendor.getVendorId());
			if (null == vendorVerification) {
				vendorVerification = new VendorVerification();
			}
			String resetCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "?purpose=resetPassword&code=" + resetCode
					+ "&type=vendor&vendorId=" + vendor.getVendorId();
			vendorVerification.setResetPasswordCreatedDate(new Timestamp(System.currentTimeMillis()));
			vendorVerification.setVendor(vendor);
			vendorVerification.setResetPasswordCode(resetCode);
			vendor.setVendorVerification(vendorVerification);
			vendorRepository.save(vendor);
			return link;
		}
		return null;
	}

}
