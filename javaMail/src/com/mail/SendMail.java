package com.mail;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
 public static void main(String[] args) throws Exception {

 
 // final String user="@gmail.com";//change accordingly
  //final String password="****";//change accordingly
	 
	 FileReader fr=new FileReader("E:/mail.properties");
		Properties prop=new Properties();
		prop.load(fr);
		
		
		
	  final String user=prop.getProperty("user");
	  final String password=prop.getProperty("password");
  
  String to="vaishnavi.pj5@gmail.com";//change accordingly
 /* Scanner sc=new Scanner(System.in);
  System.out.println("enter the to address:");
   String to=sc.nextLine();
   sc.close();*/
   //String cc="";
   //Get the session object
   Properties props = new Properties();
   props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
   
   Session session = Session.getDefaultInstance(props,
    new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication(user,password);
      }
    });

   //Compose the message
    try {
     MimeMessage message = new MimeMessage(session);
     message.setFrom(new InternetAddress(user));
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
    // message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
     message.setSubject("finally done it");
     message.setText("with java mail");
     
    //send the message
     Transport.send(message);

     System.out.println("message sent successfully...");
 
     } catch (MessagingException e) {e.printStackTrace();}
 }
}