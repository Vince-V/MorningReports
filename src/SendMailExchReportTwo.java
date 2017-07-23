/*
 * 3/17/17 - Added Singleton reference
 * 3/22/17 - Setting email name for Vincent , from spring setter injection 
 * 3/22/17 - Utilized ArrayList to loop through email address list. 
 */

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
//import java.util.Properties;
import java.util.TimerTask;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart; 

import org.jsoup.Jsoup;

//import java.util.TimerTask;

public class SendMailExchReportTwo extends TimerTask implements Runnable, EmailService{
   //public static void main(String[] args){
	FileReader reader;
	private DateTest date;
	private static ReportTwo reportTwo;
	private String emailAddress;
	private List<String> emailAddressArraylist;

	public void run(){
	  reportTwo = new ReportTwo ();	
      try {
    	  date = new DateTest();	  
 
	InternetAddress fromAddress = new InternetAddress("fromAddress@email.com",
							"fromAddress@email.com");
	InternetAddress toAddress;
 
	String msgSubject = "Production Data Load -- Exception log ";// + date.getYesterdaysDate();
	String msgBody = "Send to these addresses To:\n"
			+ " email1@email.com ;  email2@email.com; email3@email.com ; email4@email.com ; email5@email.com " 
			+ "\n" + "Cc:\n"
			+ " email6@email.com"
			+ "\n\n\nPlease find the exception log attached in the email";

//	SingletonEmail sEmail = SingletonEmail.getMySingletonEmail();
	Message msg = new MimeMessage(SingletonEmail.getEmailSession());
 
	msg.setFrom(fromAddress);
	
//	System.out.println("Starting a stream ");
//	emailAddressArraylist.stream()
//	.forEach(System.out::println);
	
//	emailAddressArraylist.forEach(i -> System.out.println("Java 8 forEach " +i));
	
	for(int i =0; i <emailAddressArraylist.size();i++){
		emailAddress = emailAddressArraylist.get(i);
		toAddress = new InternetAddress(
				emailAddress, emailAddress);
		msg.addRecipient(Message.RecipientType.BCC, toAddress);
	}	
 
	msg.setSubject(msgSubject);
	msg.setText(msgBody);
	
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	
	messageBodyPart.setText(msgBody);
 
	String filename = "C:\\Users\\YOURUSER\\Documents\\Reports\\Production Data Load Exception log.xlsx";
	String htmlText = "test";
	
	MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
    Multipart multipart = new MimeMultipart();

    messageBodyPart = new MimeBodyPart();
    
    messageBodyPart.setText(msgBody);
    
    messageBodyPart2 = new MimeBodyPart();
    String file =    "C:\\Users\\YOURUSER\\Documents\\Reports\\Production Data Load Exception log.xlsx";
    String fileName = "Production Data Load Exception log.xlsx";
    DataSource source = new FileDataSource(file);
    messageBodyPart2.setDataHandler(new DataHandler(source));
    messageBodyPart2.setFileName(fileName); //(source.getName());
    multipart.addBodyPart(messageBodyPart);
    multipart.addBodyPart(messageBodyPart2);

    msg.setContent(htmlText, "text/html; charset=utf-8");
    msg.setContent(multipart);	
 
	Transport transport = SingletonEmail.getEmailSession().getTransport("smtp");
	
	transport.connect(); 

	Calendar cal = Calendar.getInstance();
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	System.out.println("exchExcClass " +dayOfWeek);
	if ((dayOfWeek != 7) /*|| (dayOfWeek != 1)*/) {  
	transport.sendMessage(msg,  msg.getAllRecipients());
	}
//	transport.send(msg);
	transport.close();
   }
   catch (MessagingException e) {
	System.out.println(e.getMessage()+ e.getStackTrace());
   }
   catch (UnsupportedEncodingException e) {
	System.out.println(e.getMessage());
   } 
 //    catch (IOException e) {
	// System.out.println(e.getMessage());
	// e.printStackTrace();
 //   }
   
	}//end run    
	public static String extractText(Reader reader) throws IOException {
	StringBuilder sb = new StringBuilder();
	BufferedReader br = new BufferedReader(reader);
	String line;
	while ( (line=br.readLine()) != null) {
	  sb.append(line);
	}
	String textOnly = Jsoup.parse(sb.toString()).text();
	//return textOnly;
	return sb.toString();
	}
	
	@Override
	public void setEmailAddress(String emailAddress) {

		this.emailAddress = emailAddress;

		System.out.println("Inside setter method in SendMailExchReportTwo "
				+ emailAddress.toString());

		this.emailAddressArraylist = Arrays.asList(emailAddress
				.split("\\s*,\\s*"));	
	}
	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
