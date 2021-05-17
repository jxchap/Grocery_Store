package com.grocery.service;
 
import java.io.IOException;
import java.net.MalformedURLException;

import javax.mail.MessagingException;

import com.grocery.domain.Transaction;
import com.itextpdf.text.DocumentException;

public interface EmailAndPDFService {
	public void sendSimpleMessage(String to, String subject, String text);
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
	
	public String saveTransactionToPDF(Transaction transaction) throws DocumentException, MalformedURLException, IOException, MessagingException;
}
