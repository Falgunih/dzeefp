package commonActions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailReport extends ZipReport{
	TestDataFunctions data = new TestDataFunctions();
	Properties config = data.getPropertiesFileData();
	
	public void sendMail(String reportPath){
		Properties props = new Properties();
		 /*String [] reportName = reportPath.split("\\");
		 System.out.println("reportName array "+reportName.toString());
		 String finalName = reportName[3];
		 System.out.println("finalName "+finalName);*/
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("mayuri.wavhal@gslab.com", config.getProperty("emailPassword"));
					}
				});
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("mayuri.wavhal@gslab.com"));
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("bagarwal@dzeesolutions.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("mayuri.wavhal@gslab.com"));
            // Add the subject link
			message.setSubject("Financial planner demo sanity test report for "+newDate1);
			message.setText("Please find automation test report of financial planner sanity test in "+'"'+"Demo test report"+'"'+" folder of GSLABS - DZEE.");
			/*message.setContent(
					"<!DOCTYPE html> <html> <head><!--ExtentReports Library 2.41.0 | http://relevantcodes.com/extentreports-for-selenium/ | https://github.com/anshooarora/"+
					"Copyright (c) 2015, Anshoo Arora (Relevant Codes) | Copyrights licensed under the New BSD License | http://opensource.org/licenses/BSD-3-Clause"+
					"Documentation: http://extentreports.relevantcodes.com"+
				"--> <extent id='efa209a7-93be-4b06-9ea5-cb048f603f4f' />"+
				
				"<meta http-equiv='content-type' content='text/html; charset=UTF-8' /> "+
				"<meta name='description' content='ExtentReports (by Anshoo Arora) is a reporting library for automation testing for .NET and Java. It creates detailed and beautiful HTML reports for modern browsers. ExtentReports shows test and step summary along with dashboards, system and environment details for quick analysis of your tests.' />"+
				"<meta name='robots' content='noodp, noydir' />"+
				"<meta name='viewport' content='width=device-width, initial-scale=1' />"+
				"<meta name='extentx' id='extentx' content='' />"+
				"<title>"+
						"ExtentReports 2.0"+
				"</title>"+
				"<link href='./extentreports/css/css.css' type='text/css' rel='stylesheet' />"+
				"<style>" , "text/html");*/
/*			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText("Please find automation test report of financial planner sanity test in "+'"'+"Demo test report"+'"'+" folder of GSLABS - DZEE.");
			
			// creates body part for the attachment
			MimeBodyPart attachPart = new MimeBodyPart();
		    
			String attachFile = System.getProperty("user.dir")+"//test report//Report.html";
			try {
				attachPart.attachFile(attachFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			//String filename = System.getProperty("user.dir")+"//test report//Report.html";
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(reportPath);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(reportPath);
  
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
		multipart.addBodyPart(messageBodyPart1); 
 
			// add body part 2
			//multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
			*/
 
			// finally send the email
			Transport.send(message);
			Log.info("=====Email Sent=====");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}