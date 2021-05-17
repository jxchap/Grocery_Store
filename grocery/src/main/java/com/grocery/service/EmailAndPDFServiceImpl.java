package com.grocery.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.grocery.domain.Transaction;
import com.grocery.domain.TransactionContents;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EmailAndPDFServiceImpl implements EmailAndPDFService{
	
	@Autowired
	public JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Receipt.pdf", file);
		
		emailSender.send(message);		
	}
	
	public String saveTransactionToPDF(Transaction transaction) throws DocumentException, MalformedURLException, IOException, MessagingException {
		Document document = new Document();
		String fileString = "foodOrderPDFs\\Transaction_Receipt_id_" + transaction.getTransactionId() + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(fileString));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		List<Paragraph> chunks = new ArrayList<>();
		chunks.add(new Paragraph("This is a receipt to create a delivery order for: "+ transaction.getFirstName(), font));
		chunks.add(new Paragraph("Delivery order details are shown below: ", font));
		chunks.add(new Paragraph("Customer Mobile: "+ transaction.getMobileNum(), font));
		
		chunks.add(new Paragraph("Purchase Date: "+ transaction.getTransactionDate(), font));
		chunks.add(new Paragraph("Delivery Details--------------------------------------", font));
		chunks.add(new Paragraph("Address: "+ transaction.getDeliveryAddress(), font));
		chunks.add(new Paragraph("City: "+ transaction.getDeliveryCity(), font));		
		chunks.add(new Paragraph("State: "+ transaction.getDeliveryState(), font));		
		chunks.add(new Paragraph("Zip Code: "+ transaction.getDeliveryZipcode(), font));
		
		chunks.add(new Paragraph("Purchase Details--------------------------------------", font));
		chunks.add(new Paragraph("Number of Items Purchased: "+ transaction.getTransactionContents().size(), font));
		
		double grandTotal = 0 + transaction.getDeliveryCharge();
		double totalTax = 0;
		
		for(TransactionContents contents: transaction.getTransactionContents()) {
			chunks.add(new Paragraph("-", font));
			chunks.add(new Paragraph("Product ID: " + contents.getFoodItemId(), font));
			chunks.add(new Paragraph("Quantity: " + contents.getQuantity(), font));
			chunks.add(new Paragraph("Cost per Item: $" + contents.getCostPerItem(), font));

			grandTotal += contents.getTotalAmount();
			totalTax += contents.getTotalTax();
		}
		chunks.add(new Paragraph("-", font));
		chunks.add(new Paragraph("Delivery Charge: $" + transaction.getDeliveryCharge(), font));
		chunks.add(new Paragraph("Total Tax: $" + totalTax, font));
		chunks.add(new Paragraph("Grand Total: $" + grandTotal, font));
		
		
		
		for(Paragraph p : chunks) {
			document.add(p);			
		}					
						
		document.close();
		return fileString;
	}
}

