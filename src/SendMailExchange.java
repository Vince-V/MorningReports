/*
 * 3/17/17 - Added Singleton reference
 * 3/22/17 - Setting email name for Vincent , from spring setter injection 
 * 3/22/17 - Utilized ArrayList to loop through email address list. 
 */

import java.util.ArrayList;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

public class SendMailExchange extends TimerTask implements EmailService {
	// public static void main(String[] args){
	FileReader reader;
	private DateTest date;
	private Calendar cal = Calendar.getInstance();
	private static ReportThree rep810;
	private String emailAddress;
	private List<String> emailAddressArraylist;

	// public SendMailExchange(){
	// System.out.println("Inside no-arg constructor of");
	// }

	public void run() {

		rep810 = new ReportThree();
		try {
			date = new DateTest();

			InternetAddress fromAddress = new InternetAddress(
					"fromAddress@email.com",
					"fromAddress@email.com");

			InternetAddress toAddress;

			String msgSubject = "";
			String day = date.isDateSatThruMon();

			if (day == "TuesThruFri") {
				msgSubject = "Prod Data Load for "
						+ date.getYesterdaysDate();
			} else {
				msgSubject = "Prod Data Load for "
						+ date.getFridaysDateMMDD();
			}

			// SingletonEmail sEmail = SingletonEmail.getMySingletonEmail();
			// Message msg = new MimeMessage(sEmail.getEmailSession());
			Message msg = new MimeMessage(SingletonEmail.getMySingletonEmail()
					.getEmailSession());
			msg.setFrom(fromAddress);

			emailAddressArraylist.forEach(str -> System.out
					.println("From the Lambda " + str));

			for (int i = 0; i < emailAddressArraylist.size(); i++) {
				// System.out.println("size of array " +
				// emailAddressArraylist.size());
				emailAddress = emailAddressArraylist.get(i);
				toAddress = new InternetAddress(emailAddress, emailAddress);
				msg.addRecipient(Message.RecipientType.BCC, toAddress);
			}

			msg.setSubject(msgSubject);

			reader = new FileReader
			// ("C:\\Temp\\ObjectAddSearch.html");
			(
					"C:\\Users\\Documents\\Reports\\Prod_Data_Load.html");

			String htmlText = extractText(reader);

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String file = "C:\\Users\\Documents\\Reports\\Prod_Data_Load.html";
			String fileName = "REP_810";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(htmlText, "text/html; charset=utf-8");

			// Transport transport = session.getTransport("smtp"); .
			// Transport transport =
			// sEmail.getEmailSession().getTransport("smtp");
			Transport transport = SingletonEmail.getEmailSession()
					.getTransport("smtp");

			transport.connect();

			Calendar cal = Calendar.getInstance();
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println("exchangeClass day of the week " + dayOfWeek);
			if ((dayOfWeek != 7) /* || (dayOfWeek != 1) */) {
				transport.sendMessage(msg, msg.getAllRecipients());
				System.out.println("Prod Data sent!");
			}
			transport.close();
		} catch (MessagingException e) {
			System.out.println(e.getMessage() + e.getStackTrace());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}// end run

	public static String extractText(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		// String textOnly = Jsoup.parse(sb.toString()).text();
		// return textOnly;
		return sb.toString();
	}

	public void setEmailAddress(String emailAddress) {

		this.emailAddress = emailAddress;

		System.out.println("Inside setter method in Exchange "
				+ emailAddress.toString());

		this.emailAddressArraylist = Arrays.asList(emailAddress
				.split("\\s*,\\s*"));

	}

	@Override
	public String getEmailAddress() {

		return null;
	}

}
