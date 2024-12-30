package com.ibm;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Tset_Email {


	void EmailNotification() throws AddressException, IOException {
		String sub = "Test Mail";
		String MailBody = "Test Mail Body";

		//String to ="dl-ECM-SmartCaptureSupport@anthem.com";
		//String cc ="DL-FileNetLightsOnSupport@anthem.com";
		String to = "sakya.samanta@anthem.com";
		String cc = "sakya.samanta@anthem.com";
		
		String from = "DL-FileNetLightsOnSupport@anthem.com";
		String host = "smtp.wellpoint.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		String[] recipientList = to.split(",");
		int counter =0;
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		for(String recipient : recipientList) {
			recipientAddress[counter]=new InternetAddress(recipient.trim());
			counter++;
		}
		String[] recipientList1 = cc.split(",");
		int counter1 =0;
		InternetAddress[] recipientAddress1 = new InternetAddress[recipientList1.length];
		for(String recipient1 : recipientList1) {
			recipientAddress1[counter1]=new InternetAddress(recipient1.trim());
			counter1++;
		}
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			//message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.setRecipients(Message.RecipientType.CC, recipientAddress1);
			message.setSubject(sub);

			//message.setText(MailBody);
			///////////////////////////////////
			BodyPart messageBodyPart = new MimeBodyPart(); 
			messageBodyPart.setText(MailBody);

			MimeBodyPart attachmentPart1 = new MimeBodyPart();
			attachmentPart1.attachFile(new File("C:\\Automation_L1\\SizeReports.xlsx"));

			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart1);
			message.setContent(multipart);
			////////////////////////////////////////////
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
