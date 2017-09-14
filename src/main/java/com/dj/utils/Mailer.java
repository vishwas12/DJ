package com.dj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

@Configuration
public class Mailer {

	private JavaMailSender mailSender;
	
	
	@Autowired
	MailHelper mailHelper;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void prepareEmail(Object object,String templateName, String subject) {
		MimeMessagePreparator preparator =mailHelper.messagePreparator(object, templateName,subject);
		try {
			this.mailSender.send(preparator);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
