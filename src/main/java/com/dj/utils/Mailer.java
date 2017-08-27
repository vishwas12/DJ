package com.dj.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.dj.dto.User;

@Configuration
@PropertySource(Constants.APPLICATION_PROPERTIES)
public class Mailer {
	private VelocityEngine velocityEngine;

	private JavaMailSender mailSender;

	@Autowired
	Environment env;

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

	public void sendEmail(User user) {

		MimeMessagePreparator preparator = getMessagePreparator(user);
		try {
			this.mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final User user) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				String mailTo = env.getProperty(Constants.EMAIL_TO);
				message.setTo(mailTo != "" ? mailTo : user.getEmail());
				message.setFrom(new InternetAddress(env.getProperty(Constants.EMAIL_FROM)));
				message.setSubject(env.getProperty(Constants.EMAIL_SUBJECT));
				message.setSentDate(new Date());
				Map<String, Object> model = new HashMap<>();
				model.put("user", user);
				model.put("companyName", env.getProperty(Constants.COMPANY_NAME));
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity_mailTemplate.vm",
						"UTF-8", model);
				message.setText(text, true);

			}
		};
		return preparator;
	}

}
