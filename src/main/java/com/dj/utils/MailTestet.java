package com.dj.utils;

import com.dj.dto.Mail;

public class MailTestet {
	public static void main(String [] args) {
		Mailer m =  new Mailer();
		Mail mail =  new Mail();
		mail.setMailTo("ahuja.sagar14@gmail.com");
		mail.setTemplateName("velocity_mailTemplate");
		mail.setMailFrom("ahuja.sagar14@gmail.com");
		mail.setMailSubject("Test");
		m.sendMail(mail);
	}

}
