import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/Payment")

/* Payment java file is used to validate the user creditcard, place the order and send email confirmation to the given mailid */

public class Payment extends HttpServlet {	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String msg = "good";
		String Customername= "";
		String userName = "";
		HttpSession session = request.getSession(true);
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}

		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String emailid=request.getParameter("userEmail");
		System.out.print("the user address is" +userAddress);
		System.out.print(creditCardNo);
		if(session.getAttribute("usertype").equals("manager"))
		{
			Customername =request.getParameter("customername");
			userName = request.getParameter("userName");
			try{
				HashMap<String,User> hm=new HashMap<String,User>();
				hm=MySqlDataStoreUtilities.selectUser();
				if(hm.containsKey(Customername)){
					if(hm.get(Customername).getUsertype().equals("customer")){
						msg ="good";
					}else {
						if(Customername.equals(userName)){
							msg ="good";
						}else{							
							msg ="bad";
						}
					}						
				}
				else {
					msg ="bad";
				}
			}
			catch(Exception e)
			{
				
			}
		}
		String message=MySqlDataStoreUtilities.getConnection();
		System.out.println(message);
		if(message.equals("Successfull"))
		{
			if (msg.equals("good"))
			{
				int orderId=utility.getOrderPaymentSize()+1;				
				//iterate through each order
				for (OrderItem oi : utility.getCustomerOrders())
				{
					//set the parameter for each column and execute the prepared statement
					utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo,Customername,emailid);				
				}

				//remove the order details from cart after processing
					
				OrdersHashMap.orders.remove(utility.username());
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Order</a>");
				pw.print("</h2><div class='entry'>");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 14);
				Date date = cal.getTime();
				String DATE_FORMAT = "MM/dd/yyyy"; 
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);				
				String deliverydate = sdf.format(date);					
				pw.print("<h2>Your Order");
				pw.print("&nbsp&nbsp");  
				pw.print("is stored ");
				pw.print("<br>Your Order No is "+(orderId)+" and will be delivered on "+deliverydate+"</h2>");
				// Recipient's email ID needs to be mentioned.
			    String to = emailid;
				// Sender's email ID needs to be mentioned
				String from = "vavinash17@gmail.com";
				// Assuming you are sending email from localhost
				String host = "localhost";
				// Get system properties
				Properties properties = System.getProperties();
				// Setup mail server
				properties.setProperty("mail.smtp.auth","true");
				properties.setProperty("mail.smtp.starttls.enable","true"); 
				properties.setProperty("mail.smtp.host", "smtp.gmail.com");
				properties.setProperty("mail.smtp.port","587");
				// Get the default Session object.
				//Password has been changed for security purposes..
				Session session1 = Session.getDefaultInstance(properties,new javax.mail.Authenticator()
				{
					protected PasswordAuthentication  getPasswordAuthentication(){
						return new PasswordAuthentication("vavinash17@gmail.com","qwertyu");
					}

				});
				try
				{
				     // Create a default MimeMessage object.
				    MimeMessage message1 = new MimeMessage(session1);
				    // Set From: header field of the header.
				    message1.setFrom(new InternetAddress(from));
				    // Set To: header field of the header.
				    message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				    // Set Subject: header field
				    message1.setSubject("Order Conformation Details");
				    // Now set the actual message
				    message1.setContent("<div style='color:green'>Your order was placed successfully with order id: "+(orderId)+"</div><div></div>Your order will be delivered on: "+deliverydate+ "<div>Login to the application and view your order details using the orderId</div>","text/html");

				    // Send message
				    Transport.send(message1);
				    System.out.println("Sent message successfully....");
				 }
				catch (MessagingException mex) {
				    mex.printStackTrace();
				}

			}else {
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Order</a>");
				pw.print("</h2><div class='entry'>");
				pw.print("<h2>Customer does not exits</h2>");
			}		
		}
		else
		{	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h2>My Sql server is not up and running</h2>");		
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
