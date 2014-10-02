package klasser;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Epost { 
	String from,host,to;
	Session session;
	Properties properties;
	String user,pw;
	public Epost(){
		user="fellesprosjekt.gruppe15@gmail.com";
		pw="gruppe15";
		
		// get properties
		 properties = System.getProperties();
		 
		 
		 properties.setProperty("mail.smtp.auth", "true");
		 properties.setProperty("mail.smtp.starttls.enable", "true");
		// server
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		
		 session = Session.getInstance(properties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pw);
					}
				  });
		 from = "fellesprosjekt.gruppe15@gmail.com";
		
	}
		
	
	public void addRecipient(Address[] address)throws MessagingException{
		
		
	}
	public void sendMessage(String s){
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         String to[] = s.split(","); //Mail id you want to send
	         InternetAddress[] address = new InternetAddress[to.length];
	         for(int i =0; i< to.length; i++)
	         {
	             address[i] = new InternetAddress(to[i]);
	         }

	          message.setRecipients(Message.RecipientType.TO, address); 
	        
	         

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");
	         System.out.println(address);
	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	
	

	public static void main(String[] args) {
		Epost e = new Epost();
		e.sendMessage("morten23_lundenes@hotmail.com,hhhh64@hotmail.com");
		// TODO Auto-generated method stub

	}

}
