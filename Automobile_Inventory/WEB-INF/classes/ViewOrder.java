import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/ViewOrder")

/* Helper class for view,add,delete,update order */

public class ViewOrder extends HttpServlet {	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

		/*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/
	
		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		/*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			HttpSession session = request.getSession(true);
			if (request.getParameter("orderId") != null && !request.getParameter("orderId").isEmpty())
			{	
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
				try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				int size=0;			

				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				if(orderPayments.get(orderId)!=null)
				{
					for(OrderPayment od:orderPayments.get(orderId)){
						if (session.getAttribute("usertype").equals("manager"))
						{	
							size= orderPayments.get(orderId).size();
						}
						else
						{
							if(od.getUserName().equals(username)){
								size= orderPayments.get(orderId).size();
							}
						}					
					}					
				}

				// display the orders if there exist order with order id
				if(size>0)
				{	
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");						
					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			}else				
			{
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");	
			}
		}
		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			String orderName=request.getParameter("orderName");
			if(request.getParameter("orderName") != null)
			{				
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						if(oi.getOrderName().equals(orderName))
						{
							MySqlDataStoreUtilities.deleteOrder(orderId,orderName);
							ListOrderPayment.add(oi);
						}
							
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
					{
						orderPayments.remove(orderId);

					}
				//save the updated hashmap with removed order to the file	
				
				pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}

		// Add orders to the customer

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("Add"))
		{
			if(request.getParameter("productName") != null){
				Audi audi = null;
				Mercedes mercedes = null;
				Bmw Bmw = null;
				Hyundai hyundai = null;
				Jeep Jeep = null;
				Toyota toyoto = null;
				Porsche porsche = null;
				Lincoln Lincoln = null;
				HeadLight HeadLight = null;
				Rims Rims = null;
				Windowtint Windowtint = null;
				Backlight Backlight = null;
				Exhaust Exhaust = null;
				Accessory accessory = null;
                String name = request.getParameter("productName");
				audi = MySqlDataStoreUtilities.getAudis().get(name);
				mercedes = MySqlDataStoreUtilities.getMercedes().get(name);
				Bmw = MySqlDataStoreUtilities.getBmws().get(name);
				hyundai = MySqlDataStoreUtilities.getHyundais().get(name);
				Jeep = MySqlDataStoreUtilities.getJeeps().get(name);
				toyoto = MySqlDataStoreUtilities.getToyotas().get(name);
				porsche = MySqlDataStoreUtilities.getPorsches().get(name);
				Lincoln = MySqlDataStoreUtilities.getLincolns().get(name);
				HeadLight = MySqlDataStoreUtilities.getHeadlights().get(name);
				Rims = MySqlDataStoreUtilities.getRims().get(name);
				Windowtint = MySqlDataStoreUtilities.getWindowtints().get(name);
				Backlight = MySqlDataStoreUtilities.getBacklights().get(name);
				Exhaust = MySqlDataStoreUtilities.getExhausts().get(name);
				accessory = MySqlDataStoreUtilities.getAccessories().get(name);
				String username1=request.getParameter("userName");
				Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				if(mercedes != null){
					System.out.print(mercedes.getName());
					System.out.print(mercedes.getPrice());
					System.out.print(mercedes.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,mercedes.getName(),mercedes.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(audi !=null){
					System.out.print(audi.getName());
					System.out.print(audi.getPrice());
					System.out.print(audi.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,audi.getName(),audi.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(hyundai !=null){
					System.out.print(hyundai.getName());
					System.out.print(hyundai.getPrice());
					System.out.print(hyundai.getRetailer());
					
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,hyundai.getName(),hyundai.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Bmw !=null){
					System.out.print(Bmw.getName());
					System.out.print(Bmw.getPrice());
					System.out.print(Bmw.getRetailer());					
					try                       
					{							
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Bmw.getName(),Bmw.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Jeep !=null){
					System.out.print(Jeep.getName());
					System.out.print(Jeep.getPrice());
					System.out.print(Jeep.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Jeep.getName(),Jeep.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(toyoto !=null){
					System.out.print(toyoto.getName());
					System.out.print(toyoto.getPrice());
					System.out.print(toyoto.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,toyoto.getName(),toyoto.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(porsche != null){
					System.out.print(porsche.getName());
					System.out.print(porsche.getPrice());
					System.out.print(porsche.getRetailer());					
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,porsche.getName(),porsche.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Lincoln !=null){
					System.out.print(Lincoln.getName());
					System.out.print(Lincoln.getPrice());
					System.out.print(Lincoln.getRetailer());
					
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Lincoln.getName(),Lincoln.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(HeadLight !=null){
					System.out.print(HeadLight.getName());
					System.out.print(HeadLight.getPrice());
					System.out.print(HeadLight.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,HeadLight.getName(),HeadLight.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Rims !=null){
					System.out.print(Rims.getName());
					System.out.print(Rims.getPrice());
					System.out.print(Rims.getRetailer());					
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Rims.getName(),Rims.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");						
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Windowtint !=null){
					System.out.print(Windowtint.getName());
					System.out.print(Windowtint.getPrice());
					System.out.print(Windowtint.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Windowtint.getName(),Windowtint.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}


				else if(Backlight !=null){
					System.out.print(Backlight.getName());
					System.out.print(Backlight.getPrice());
					System.out.print(Backlight.getRetailer());					
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Backlight.getName(),Backlight.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(Exhaust !=null){
					System.out.print(Exhaust.getName());
					System.out.print(Exhaust.getPrice());
					System.out.print(Exhaust.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,Exhaust.getName(),Exhaust.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else if(accessory !=null){
					System.out.print(accessory.getName());
					System.out.print(accessory.getPrice());
					System.out.print(accessory.getRetailer());
					try                       
					{	
						MySqlDataStoreUtilities.insertOrder(orderId,username1,accessory.getName(),accessory.getPrice(),"chicago","1234567812345678","vavinash17@gmail.com");
						pw.print("<h4 style='color:red'>Order Has been added..</h4>");
					}
					catch(Exception e)
					{
						System.out.println("inside exception file not written properly");
					}	
				}
				else{
					pw.print("<h4 style='color:red'>Enter correct product name</h4>");
				}
			}
			else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}			
		}
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

}


