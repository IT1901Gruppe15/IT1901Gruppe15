package core;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Klasse for å kunne sende epost
 *
 */
public class Epost { 
	private String from,host,to;
	private Session session;
	private Properties properties;
	private String user,pw,msg,sub;
	
	
	/**
	 * Kontstrukør for å sette opp epost med server og lignende
	 * 
	 */
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
		
	
	/**setter melding
	 * @param msg meldings innhold
	 */
	public void setMes(String msg){
		this.msg=msg;
		
	}
	
	/**setter emne(subject) til meldingen
	 * @param sub emne(subject) til meldingen
	 */
	public void setSub(String sub){
		this.sub=sub;
		
		
	}
	
	/**
	 *  Metode for å sende melding med muligheter for flere mottakere ved å bruke , mellom mottakere
	 * @param mottakere setter mottakere av melding
	 */
	public void sendMessage(String mottakere){
		
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         String to[] = mottakere.split(","); //Mail id you want to send
	         InternetAddress[] address = new InternetAddress[to.length];
	         for(int i =0; i< to.length; i++)
	         {
	             address[i] = new InternetAddress(to[i]);
	         }

	          message.setRecipients(Message.RecipientType.TO, address); 
	        
	         

	         // Set Subject: header field
	         message.setSubject(sub);

	         // Now set the actual message
	         message.setText(msg);
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
