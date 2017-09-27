package com.dj.utils;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dj.dao.UserRepository;
import com.dj.dao.UserVerificationRepository;
import com.dj.dto.User;
import com.dj.dto.UserVerification;

@Service
@PropertySource(Constants.APPLICATION_PROPERTIES)
public class UserMailerDBService {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	Environment env;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserVerificationRepository userVerificationRepository;

	public String handleDbOperations(User user, String templateName) {
		if (templateName.equals(Constants.EMAIL_VERIFICATION)) {

			UserVerification userVerification = userVerificationRepository.findByUserId(user.getUserId());
			if (null == userVerification) {
				userVerification = new UserVerification();
			}
			String verificationCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "?purpose=verifyemail&code=" + verificationCode
					+ "&type=user&userId=" + user.getUserId();
			userVerification.setVerificationCodeCreatedDate(new Timestamp(System.currentTimeMillis()));
			userVerification.setUser(user);
			userVerification.setVerificationCode(verificationCode);
			user.setUserVerification(userVerification);
			userRepository.save(user);
			return link;

		} else if (templateName.equals(Constants.FORGOT_PASSWORD)) {
			UserVerification userVerification = userVerificationRepository.findByUserId(user.getUserId());
			if (null == userVerification) {
				userVerification = new UserVerification();
			}
			String resetCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "?purpose=resetPassword&code=" + resetCode
					+ "&type=user&userId=" + user.getUserId();
			userVerification.setResetPasswordCreatedDate(new Timestamp(System.currentTimeMillis()));
			userVerification.setUser(user);
			userVerification.setResetPasswordCode(resetCode);
			user.setUserVerification(userVerification);
			userRepository.save(user);
			return link;
		}

		return null;
	}

}
