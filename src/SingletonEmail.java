/*
 * created 3/17/17 - by Vincent V.
 */
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SingletonEmail {

	private static SingletonEmail sEmail; // = new SingletonEmail();	

	private SingletonEmail(){
		
	}
	
	public static SingletonEmail getMySingletonEmail(){
		
		if (sEmail == null){
			synchronized(SingletonEmail.class) {
				if (sEmail == null)
					sEmail = new SingletonEmail();
			}
		}
 
		return sEmail;
	}
	
	public static Session getEmailSession(){
		
		Properties props =  new Properties();
		props.put("mail.smtp.host", "aserver.company.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "aserver.company.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.connectiontimeout", "10000");

		final String EmailUser = "USER";
		final String EmailPassword = "Password";

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				  protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
						EmailUser,EmailPassword);
				     }
			   });

//		session.setDebug(true);	
		
		return session;
	}
	
}
