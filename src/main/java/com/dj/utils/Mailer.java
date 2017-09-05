package com.dj.utils;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.dj.dao.UserRepository;
import com.dj.dao.VendorRepository;
import com.dj.dto.User;
import com.dj.dto.UserVerification;
import com.dj.dto.Vendor;
import com.dj.dto.VendorVerification;

@Configuration
@PropertySource(Constants.APPLICATION_PROPERTIES)
public class Mailer {
	private VelocityEngine velocityEngine;

	private JavaMailSender mailSender;

	@Autowired
	Environment env;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	/*@PersistenceContext(unitName="entityManagerFactory")
	EntityManager em;*/
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void prepareEmail(Object object) {
		MimeMessagePreparator preparator =null;
		if(object instanceof User) {
			
			User user = (User) object;
			String verificationCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "verifyemail?code=" + verificationCode + "&type=user&userId="+ user.getUserId();
			UserVerification userVerification = new UserVerification();
			userVerification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			userVerification.setUser(user);
			userVerification.setVerificationCode(verificationCode);
			user.setUserVerification(userVerification);
			userRepository.save(user);
			preparator = getMessagePreparator(user.getFirstName(),link,user.getEmail());
			
		}
		else if (object instanceof Vendor){
			
			Vendor vendor = (Vendor) object;
			String verificationCode = UUID.randomUUID().toString();
			String link = env.getProperty(Constants.DOMAIN_NAME) + "verifyemail?code=" + verificationCode + "&type=vendor&vendorId="+ vendor.getVendorId();
			VendorVerification verification =  new VendorVerification();
			verification.setVerificationCode(verificationCode);
			verification.setVendor(vendor);
			verification.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			vendor.setVendorVerification(verification);
			vendorRepository.save(vendor);
			preparator = getMessagePreparator(vendor.getFirstName(),link,vendor.getEmail());
		}
		
		try {
			this.mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
	@Transactional
	private MimeMessagePreparator getMessagePreparator(final String firstName,final String link,final String email) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				message.setTo(email);
				message.setFrom(new InternetAddress(env.getProperty(Constants.EMAIL_FROM)));
				message.setSubject(env.getProperty(Constants.EMAIL_SUBJECT));
				message.setSentDate(new Date());
				
				VelocityContext velocityContext = new VelocityContext();
		        velocityContext.put("firstName", firstName);
		        velocityContext.put("companyName", env.getProperty(Constants.COMPANY_NAME));
		        velocityContext.put("link", link);
		        StringWriter stringWriter = new StringWriter();
		        
				velocityEngine.mergeTemplate("velocity_mailTemplate.vm","UTF-8", velocityContext, stringWriter);
				
				message.setText(stringWriter.toString(), true);

			}
		};
		return preparator;
	}

}
