package com.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMail {
public boolean sendmail(String recepient,String msg) {
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true" );
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		String username="sundarav61@gmail.com";
		String password="sxeyduoesihmshlh";
		Session session=Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		Message message=prepareMessage(session,username,recepient,msg);
		try {
			
			Transport.send(message);
			return true;
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return false;
		}
		
	}

	private static Message prepareMessage(Session session, String username, String recepient,String msg) {
		// TODO Auto-generated method stub
		Message message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("Booking Servive");
			message.setText(msg);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return message;
	}
}
