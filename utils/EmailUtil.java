package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Description   : Super class for script helper
 * 
 * @author mquraishi
 * @since  September 10, 2014
 */
public  class EmailUtil 
{
	static final String username = "dataflowtester01@gmail.com";
	static final String password = "qatester";

	static Properties props = new Properties();
	
	public EmailUtil()
	{
		/*props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");*/
		
	}
	
	public static void send(String subject, String body )
	{
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dataflowtester01@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("qaautomation@book.com"));
			message.setSubject(subject);
			message.setText(body);
	
			
			Transport.send(message);
	
			//System.out.println("Done");
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
