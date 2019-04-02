import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}

	//Printhtml is used to complete the remaining part of the header section with conditions

	public void printHtml(String file) {
		String result = HtmlToString(file);
		String username=null;
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
			if (session.getAttribute("username")!=null){
				username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='avicl'>ViewOrder</span></a></li>"
						+ "<li><a href='Account'><span class='avicl'>Account</span></a></li>";
				if(session.getAttribute("usertype").equals("manager")){
					result=result + "<li><a href='Registration'><span class='avicl'>AddCustomer</span></a></li>";
				}
				if(session.getAttribute("usertype").equals("manager"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='avicl'>Add product</span></a></li>"
						+ "<li style='background-color: black !important;'><a href='ProductModify?button=Updateproduct'><span class='avicl'>Update product</span></a></li>"
						+"<li style='background-color: black !important;'><a href='ProductModify?button=Deleteproduct'><span class='avicl'>Delete product</span></a></li>"
						+"<li style='background-color: black !important;'><a href='DataAnalytics'><span class='avicl'>Data Analytics</span></a></li>"
						+"<li style='background-color: black !important;'><a href='DataVisualization'><span class='avicl'>Data Visualization</span></a></li>"
						+"<li class='' style='background-color: black !important;'><div class='dropdown'><a class='avicl'>Sales Report</a><div class='dropdown-content' style='border-bottom: 1px solid black;border-right: 1px solid black;border-left: 1px solid black;'><a href='SalesReport?button=ProductAvailability'><span class='avicl'>Product Sold</span></a><a href='SalesReport?button=GraphicalView'><span class='avicl'>Graphical View</span></a><a href='SalesReport?button=SalesByDate'><span class='avicl'>Sales By Date</span></a></div></div></li>"
						+"<li class='' style='background-color: black !important;'><div class='dropdown'><a class='avicl'>Inventory Report</a><div class='dropdown-content' style='border-bottom: 1px solid black;border-right: 1px solid black;border-left: 1px solid black;'><a href='InventoryReport?button=ProductsInventory'><span class='avicl'>Products Inventory</span></a><a href='InventoryReport?button=GraphicalView'><span class='avicl'>Graphical View</span></a><a href='InventoryReport?button=AllProductsOnSale'><span class='avicl'>Products On Sale</span></a><a href='InventoryReport?button=ManufacturerRebates'><span class='avicl'>Manufacturer Rebates</span></a></div></div></li>";
				}
				result=result + "<li style='background-color: black !important;'><a href='Logout'><span class='avicl'>Logout</span></a></li>";
			}
			else
				result = result +"<li style='background-color: black !important;'><a href='ViewOrder'><span class='avicl'>ViewOrder</span></a></li>"+ "<li><a href='Login'><span class='avicl'>Login</span></a></li>";
				result = result +"<div align='right'>"+"<li style='background-color: black !important;'><a href='Cart'><span class='glyphicon glyphicon-shopping-cart'>("+CartCount()+")</span></a></li></div></ul></nav>";
				if(username!=null){
					result=result+"<div style='text-align: right;'><span class='userstyle'>Logged in as "+username+"</span></div><div id='page' style='margin-top: 20px;'>";
				}else{
					result=result+"<div id='page' style='margin-top: 20px;'>";
				}
				pw.print(result);
		}else
				pw.print(result);
	}

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);
		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/

	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	public void removeOrder(int i){
		ArrayList<OrderItem> allorder= getCustomerOrders();
			System.out.println(i);
		 allorder.remove(i);
		 return;
	}


	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		int size=0;
			try
			{
				orderPayments =MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}			
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=entry.getKey();					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());		
		HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
		HashMap<String,Mercedes> allmercedes=new HashMap<String,Mercedes>();
		HashMap<String,Bmw> allbmws=new HashMap<String,Bmw>();
		HashMap<String,Audi> allaudis=new HashMap<String,Audi>();
		HashMap<String,Jeep> alljeeps=new HashMap<String,Jeep>();
		HashMap<String,Hyundai> allhyundais=new HashMap<String,Hyundai>();
		HashMap<String,Toyota> alltoyotas=new HashMap<String,Toyota>();
		HashMap<String,Porsche> allporsches=new HashMap<String,Porsche>();
		HashMap<String,Lincoln> alllincolns=new HashMap<String,Lincoln>();
		HashMap<String,HeadLight> allheadlights=new HashMap<String,HeadLight>();
		HashMap<String,Rims> allrims=new HashMap<String,Rims>();
		HashMap<String,Windowtint> allwindowtints=new HashMap<String,Windowtint>();
		HashMap<String,Backlight> allbacklights=new HashMap<String,Backlight>();
		HashMap<String,Exhaust> allexhaust=new HashMap<String,Exhaust>();
		HashMap<String,Newcars> allnewcars=new HashMap<String,Newcars>();
		HashMap<String,Usedcars> allusedcars=new HashMap<String,Usedcars>();
		if(type.equals("mercedes")){
			Mercedes mercedes = null;
			try{
			allmercedes = MySqlDataStoreUtilities.getMercedes();			
			}
			catch(Exception e){				
			}
			mercedes = allmercedes.get(name);
			OrderItem orderitem = new OrderItem(mercedes.getName(), mercedes.getPrice(), mercedes.getImage(), mercedes.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("bmw")){
			Bmw bmw = null;
			try{
			allbmws = MySqlDataStoreUtilities.getBmws();			
			}
			catch(Exception e){				
			}
			bmw = allbmws.get(name);
			OrderItem orderitem = new OrderItem(bmw.getName(), bmw.getPrice(), bmw.getImage(), bmw.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("audi")){
			Audi audi = null;
			try{
			allaudis = MySqlDataStoreUtilities.getAudis();			
			}
			catch(Exception e){				
			}
			audi = allaudis.get(name);
			OrderItem orderitem = new OrderItem(audi.getName(), audi.getPrice(), audi.getImage(), audi.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("jeep")){
			Jeep jeep = null;
			try{
			alljeeps = MySqlDataStoreUtilities.getJeeps();			
			}
			catch(Exception e){				
			}
			jeep = alljeeps.get(name);
			OrderItem orderitem = new OrderItem(jeep.getName(), jeep.getPrice(), jeep.getImage(), jeep.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("hyundai")){
			Hyundai hyundai = null;
			try{
			allhyundais = MySqlDataStoreUtilities.getHyundais();			
			}
			catch(Exception e){				
			}
			hyundai = allhyundais.get(name);
			OrderItem orderitem = new OrderItem(hyundai.getName(), hyundai.getPrice(), hyundai.getImage(), hyundai.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("toyota")){
			Toyota toyota = null;
			try{
			alltoyotas = MySqlDataStoreUtilities.getToyotas();			
			}
			catch(Exception e){				
			}
			toyota = alltoyotas.get(name);
			OrderItem orderitem = new OrderItem(toyota.getName(), toyota.getPrice(), toyota.getImage(), toyota.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("porsche")){
			Porsche porsche = null;
			try{
			allporsches = MySqlDataStoreUtilities.getPorsches();			
			}
			catch(Exception e){				
			}
			porsche = allporsches.get(name);
			OrderItem orderitem = new OrderItem(porsche.getName(), porsche.getPrice(), porsche.getImage(), porsche.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("lincoln")){
			Lincoln lincoln = null;
			try{
			alllincolns = MySqlDataStoreUtilities.getLincolns();			
			}
			catch(Exception e){				
			}
			lincoln = alllincolns.get(name);
			OrderItem orderitem = new OrderItem(lincoln.getName(), lincoln.getPrice(), lincoln.getImage(), lincoln.getRetailer());
			orderItems.add(orderitem);
		}
		

		if(type.equals("headlight")){
			HeadLight headlight = null;
			try{
			allheadlights = MySqlDataStoreUtilities.getHeadlights();			
			}
			catch(Exception e){				
			}
			headlight = allheadlights.get(name);
			OrderItem orderitem = new OrderItem(headlight.getName(), headlight.getPrice(), headlight.getImage(), headlight.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("rims")){
			Rims rims = null;
			try{
			allrims = MySqlDataStoreUtilities.getRims();			
			}
			catch(Exception e){				
			}
			rims = allrims.get(name);
			OrderItem orderitem = new OrderItem(rims.getName(), rims.getPrice(), rims.getImage(), rims.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("windowtint")){
			Windowtint windowtint = null;
			try{
			allwindowtints = MySqlDataStoreUtilities.getWindowtints();			
			}
			catch(Exception e){				
			}
			windowtint = allwindowtints.get(name);
			OrderItem orderitem = new OrderItem(windowtint.getName(), windowtint.getPrice(), windowtint.getImage(), windowtint.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("backlight")){
			Backlight backlight = null;
			try{
			allbacklights = MySqlDataStoreUtilities.getBacklights();			
			}
			catch(Exception e){				
			}
			backlight = allbacklights.get(name);
			OrderItem orderitem = new OrderItem(backlight.getName(), backlight.getPrice(), backlight.getImage(), backlight.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("exhaust")){
			Exhaust exhaust = null;
			try{
			allexhaust = MySqlDataStoreUtilities.getExhausts();			
			}
			catch(Exception e){				
			}
			exhaust = allexhaust.get(name);
			OrderItem orderitem = new OrderItem(exhaust.getName(), exhaust.getPrice(), exhaust.getImage(), exhaust.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("newcars")){
			Newcars newcars = null;
			try{
			allnewcars = MySqlDataStoreUtilities.getNewcars();			
			}
			catch(Exception e){				
			}
			newcars = allnewcars.get(name);
			OrderItem orderitem = new OrderItem(newcars.getName(), newcars.getPrice(), newcars.getImage(), newcars.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("Usedcars")){
			Usedcars usedcars = null;
			try{
			allusedcars = MySqlDataStoreUtilities.getUsedcars();			
			}
			catch(Exception e){				
			}
			usedcars = allusedcars.get(name);
			OrderItem orderitem = new OrderItem(usedcars.getName(), usedcars.getPrice(), usedcars.getImage(), usedcars.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("accessories")){	
			Accessory accessory = null;
			try{
			allaccessories = MySqlDataStoreUtilities.getAccessories();			
			}
			catch(Exception e){				
			}

			accessory = allaccessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}

	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo,String customer,String emailid){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}

			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
			OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,emailid);
			listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try                       
			{	
				if(session.getAttribute("usertype").equals("manager"))
				{
					MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo,emailid);
				}else{
					MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,emailid);
				}
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	public String storeReview(String userid,String productname,String producttype,String productprice,String retailername,String manufacturername,String reviewrating,String userage,String usergender,String useroccupation,String productonsale,String manufacturerrebate,String zipcode,String retailercity,String retailerstate,String reviewdate,String reviewtext){
	String message=MongoDBDataStoreUtilities.insertReview(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
		if(!message.equals("Successfull"))
		{ 
			return "UnSuccessfull";
		}
		else
		{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{
				
			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}

			// if there exist product review already add it into same list for productname or create a new record with product name
				
			if(!reviews.containsKey(productname)){	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(productname, arr);
			}
			ArrayList<Review> listReview = reviews.get(productname);		
			Review review = new Review(userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext);
			listReview.add(review);					
			// add Reviews into database			
			return "Successfull";	
		}
	}

}
