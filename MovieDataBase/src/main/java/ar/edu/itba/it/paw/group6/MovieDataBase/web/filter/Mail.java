package ar.edu.itba.it.paw.group6.MovieDataBase.web.filter;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mail {

	/*
	Some SMTP servers require a username and password authentication before you
	can use their Server for Sending mail. This is most common with couple
	of ISP's who provide SMTP Address to Send Mail.

	This Program gives any example on how to do SMTP Authentication
	(User and Password verification)

	This is a free source code and is provided as it is without any warranties and
	it can be used in any your code for free.

	Author : Sudhir Ancha
	*/


	/*
	  To use this program, change values for the following three constants,

	    SMTP_HOST_NAME -- Has your SMTP Host Name
	    SMTP_AUTH_USER -- Has your SMTP Authentication UserName
	    SMTP_AUTH_PWD  -- Has your SMTP Authentication Password

	  Next change values for fields

	  emailMsgTxt  -- Message Text for the Email
	  emailSubjectTxt  -- Subject for email
	  emailFromAddress -- Email Address whose name will appears as "from" address

	  Next change value for "emailList".
	  This String array has List of all Email Addresses to Email Email needs to be sent to.


	  Next to run the program, execute it as follows,

	  SendMailUsingAuthentication authProg = new SendMailUsingAuthentication();

	*/


	  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	  private static final String SMTP_AUTH_USER = "moviewallsupport";
	  private static final String SMTP_AUTH_PWD  = "danielazar";
	  


	  public void postMail( String recipients[ ], String subject,
	                            String message , String from) throws MessagingException
	  {
	    boolean debug = false;

	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("mail.smtp.host", SMTP_HOST_NAME);
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.transport.protocol", "smtps");
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.quitwait", "false");
			Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);

	    session.setDebug(debug);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length];
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);

	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    
	    Transport.send(msg);
	 }


	/**
	* SimpleAuthenticator is used to do simple authentication
	* when the SMTP server requires it.
	*/
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}

	


}
