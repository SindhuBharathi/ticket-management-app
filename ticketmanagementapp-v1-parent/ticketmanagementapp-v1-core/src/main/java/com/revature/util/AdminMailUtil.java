package com.revature.util;

import java.util.logging.Logger;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class AdminMailUtil {
	private AdminMailUtil() {
		throw new IllegalAccessError("Utility class");
	}

	public static void sendSimpleMail(String mail, String subject, String description, int ticketId) throws EmailException {
		Email email = new SimpleEmail();
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(MailConstants.emailId, MailConstants.password));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.setSSLOnConnect(true);
		email.setFrom(mail);
		email.setSubject("Ticket created");
		email.setMsg("TicketId: "+ticketId+" Subject: "+subject+" Description: "+description);
		email.addTo(MailConstants.emailId);
		email.setStartTLSEnabled(true);
		email.send();
		Logger logger = Logger.getLogger(MailUtil.class.getName());
		logger.info("Mail sent");
	}


}
