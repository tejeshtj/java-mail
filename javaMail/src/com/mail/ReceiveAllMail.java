package com.mail;


import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveAllMail {
  

public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);

	
	System.out.println("enter your mail i'd:");
	String email_id =sc.nextLine();

	System.out.println("enter your password:");
     String password = sc.nextLine();
     sc.close();
 //set properties 
 Properties properties = new Properties();
 //You can use imap or imaps , *s -Secured
 properties.put("mail.store.protocol", "imaps");
 //Host Address of Your Mail
 properties.put("mail.imaps.host", "imap.gmail.com");
 //Port number of your Mail Host
 properties.put("mail.imaps.port", "993");

 //properties.put("mail.imaps.timeout", "10000");

 try {

  //create a session  
  Session session = Session.getDefaultInstance(properties, null);
  //SET the store for IMAPs
  Store store = session.getStore("imaps");

  System.out.println("Connection initiated......");
  //Trying to connect IMAP server
  store.connect(email_id, password);
  System.out.println("Connection is ready :)");


  //Get inbox folder 
  Folder inbox = store.getFolder("inbox");
  //SET readonly format (*You can set read and write)
  inbox.open(Folder.READ_ONLY);


  //Display email Details 

  //Inbox email count
  int messageCount = inbox.getMessageCount();
  System.out.println("Total Messages in INBOX:- " + messageCount);

  //Print Last 10 email information
  for (int i = messageCount; i>0; i--) {
   System.out.println("Mail Subject:- " + inbox.getMessage(i).getSubject());
   System.out.println("Mail From:- " + inbox.getMessage( i).getFrom()[0]);
   System.out.println("Mail Content:- " + inbox.getMessage(i).getContent().toString());
   System.out.println("------------------------------------------------------------");
  }

  inbox.close(true);
  store.close();

 } catch (Exception e) {
  e.printStackTrace();
 }




}
}
