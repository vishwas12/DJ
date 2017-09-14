package com.dj.utils;

import java.io.StringWriter;
import java.util.Date;

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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.dj.dto.User;
import com.dj.dto.Vendor;

@Configuration
@PropertySource(Constants.APPLICATION_PROPERTIES)
public class MailHelper {

	@Autowired
	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	Environment env;
	@Autowired
	UserMailerDBService userMailerDBService;

	@Autowired
	VendorMailerDBService vendorMailerDBService;

	public MimeMessagePreparator messagePreparator(Object object, String templateName, String subject) {
		MimeMessagePreparator preparator = null;
		String link = null;
		if (object instanceof User) {
			User user = (User) object;
			link = userMailerDBService.handleDbOperations(user, templateName);
			preparator = getMessagePreparator(user.getFirstName(), link, user.getEmail(), templateName, subject);

		} else if (object instanceof Vendor) {
			Vendor vendor = (Vendor) object;
			link = vendorMailerDBService.handleDbOperations((Vendor) object, templateName);
			preparator = getMessagePreparator(vendor.getFirstName(), link, vendor.getEmail(), templateName, subject);
		}
		return preparator;
	}

	@Transactional
	private MimeMessagePreparator getMessagePreparator(final String firstName, final String link, final String email,
			final String templateName, final String subject) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(email);
				message.setFrom(new InternetAddress(env.getProperty(Constants.EMAIL_FROM)));
				message.setSubject(subject);
				message.setSentDate(new Date());

				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("firstName", firstName);
				velocityContext.put("companyName", env.getProperty(Constants.COMPANY_NAME));
				velocityContext.put("link", link);
				StringWriter stringWriter = new StringWriter();

				velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, stringWriter);

				message.setText(stringWriter.toString(), true);

			}
		};
		return preparator;
	}

}
