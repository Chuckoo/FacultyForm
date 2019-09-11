package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailConfig {

	public static String hostMailID = "vikram.is17@bmsce.ac.in";
	public static String hostMailPdw = "dragon@27";
	
	public static void setHostInfo(String ID,String pwd) {
		hostMailID=ID;
		hostMailPdw=pwd;
	}
	

	public static Session getAuth () {
		

	    String host = "smtp.gmail.com";
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.user", hostMailID);
	    props.put("mail.password", hostMailPdw);
	    props.put("mail.smtp.port", "587");

	    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(hostMailID, hostMailPdw);
	        }
	    });
	    
	    return mailSession;
	}
	
	public static void sendMail(String to,String subject,String messg) {
		Session	mailSession = getAuth();
		try {
	        MimeMessage message = new MimeMessage(mailSession);
	        message.setFrom(new InternetAddress(hostMailID));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject(subject);
	        message.setText(messg);
	        Transport.send(message);

	    } catch (MessagingException mex) {

	    	System.err.println("Got an exception! Error in mailConfig/sendMail()");
            System.err.println(mex.getMessage());
	    }
	}
}
