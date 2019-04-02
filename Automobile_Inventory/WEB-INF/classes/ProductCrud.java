import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

/* ProductCrud class is a helper class for Add,Delete and Update Products in the Inventory.Enabeled for StoreManager */

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String action = request.getParameter("button");			
		String msg = "good";
		String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "";
		double productPrice=0.0,productDiscount = 0.0;
		HashMap<String,Mercedes> allmercedes = new HashMap<String,Mercedes> ();
		HashMap<String,Bmw> allbmws = new HashMap<String,Bmw> ();
		HashMap<String,Audi> allaudis = new HashMap<String,Audi> ();
		HashMap<String,Jeep> alljeeps = new HashMap<String,Jeep> ();
		HashMap<String,Hyundai> allhyundais = new HashMap<String,Hyundai> ();
		HashMap<String,Toyota> alltoyotas = new HashMap<String,Toyota> ();
		HashMap<String,Porsche> allporsches = new HashMap<String,Porsche> ();
		HashMap<String,Lincoln> alllincolns = new HashMap<String,Lincoln> ();
		HashMap<String,HeadLight> allheadlights = new HashMap<String,HeadLight> ();
		HashMap<String,Rims> allrims = new HashMap<String,Rims> ();
		HashMap<String,Windowtint> allwindowtints = new HashMap<String,Windowtint> ();
		HashMap<String,Backlight> allbacklight = new HashMap<String,Backlight> ();
		HashMap<String,Exhaust> allexhausts = new HashMap<String,Exhaust> ();
		HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
		/* Getting required variable values from the session for add and update products */
		if (action.equals("add") || action.equals("update"))
		{	
			producttype = request.getParameter("producttype");
			productId   = request.getParameter("productId");
			productName = request.getParameter("productName"); 
			productPrice = Double.parseDouble(request.getParameter("productPrice"));
			productImage = request.getParameter("productImage");
			productManufacturer = request.getParameter("productManufacturer");
			productCondition = request.getParameter("productCondition");
			productDiscount = Double.parseDouble(request.getParameter("productDiscount"));			 
		}
		else
		{
			productId   = request.getParameter("productId");
		}	
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("add"))
		{
  			if(producttype.equals("mercedes")){
				allmercedes = MySqlDataStoreUtilities.getMercedes();
			  	if(allmercedes.containsKey(productId)){
			  	msg = "Product already available";				  
		 	}
				  
		  	}
		  	else if(producttype.equals("bmw"))
		  	{
			  	allbmws = MySqlDataStoreUtilities.getBmws();
			  	if(allbmws.containsKey(productId)){
				  	msg = "Product already available";
			  	}
		  	}
		  	else if (producttype.equals("audi"))
		  	{
			  allaudis = MySqlDataStoreUtilities.getAudis();
			  if(allaudis.containsKey(productId)){
				  msg = "Product already available";
			  }
		  	}
			else if (producttype.equals("jeep"))
			{
			  alljeeps = MySqlDataStoreUtilities.getJeeps();
			  if(alljeeps.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("hyundai"))
			{
			  allhyundais = MySqlDataStoreUtilities.getHyundais();
			  if(allhyundais.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("toyota"))
			{
			  alltoyotas = MySqlDataStoreUtilities.getToyotas();
			  if(alltoyotas.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("porsche"))
			{
			  allporsches = MySqlDataStoreUtilities.getPorsches();
			  if(allporsches.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("lincoln"))
			{
			  alllincolns = MySqlDataStoreUtilities.getLincolns();
			  if(alllincolns.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("headlight"))
			{
			  allheadlights = MySqlDataStoreUtilities.getHeadlights();
			  if(allheadlights.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("rims"))
			{
			  allrims = MySqlDataStoreUtilities.getRims();
			  if(allrims.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("windowstints"))
			{
			  allwindowtints = MySqlDataStoreUtilities.getWindowtints();
			  if(allwindowtints.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("backlights"))
			{
			  allbacklight = MySqlDataStoreUtilities.getBacklights();
			  if(allbacklight.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("exhaust"))
			{
			  allexhausts = MySqlDataStoreUtilities.getExhausts();
			  if(allexhausts.containsKey(productId)){
				  msg = "Product already available";
			  }
			}
			else if (producttype.equals("accessories"))
			{  
				if(!request.getParameter("product").isEmpty())
					{
						prod = request.getParameter("product");
						allmercedes = MySqlDataStoreUtilities.getMercedes();
						if(allmercedes.containsKey(prod))
						{
							allaccessories = MySqlDataStoreUtilities.getAccessories();
							if(allaccessories.containsKey(productId)){
								msg = "Product already available";
							}
						}else{
							msg = "The product related to accessories is not available";
						}
					}
					else{
						msg = "Please add the prodcut name";
					}			  
			}	
			if (msg.equals("good"))
			{  
				try
				{
				  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
				}
				catch(Exception e)
				{ 
					msg = "Product cannot be inserted";
				}
				msg = "Product has been successfully added";
			}					
			}
			else if(action.equals("update"))
			{

				if(producttype.equals("mercedes")){
			  		allmercedes = MySqlDataStoreUtilities.getMercedes();
			  		if(!allmercedes.containsKey(productId)){
				 		msg = "Product not available";
			  		}
				  
				}else if(producttype.equals("bmw"))
				{
				  	allbmws = MySqlDataStoreUtilities.getBmws();
				  	if(!allbmws.containsKey(productId)){
					 	msg = "Product not available";
				  	}
				}else if (producttype.equals("audi"))
				{
				  allaudis = MySqlDataStoreUtilities.getAudis();
				  if(!allaudis.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("jeep"))
				{
				  alljeeps = MySqlDataStoreUtilities.getJeeps();
				  if(!alljeeps.containsKey(productId)){
					  msg = "Product not available";
				  }
				}

				else if (producttype.equals("hyundai"))
				{
				  allhyundais = MySqlDataStoreUtilities.getHyundais();
				  if(!allhyundais.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("toyota"))
				{
				  alltoyotas = MySqlDataStoreUtilities.getToyotas();
				  if(!alltoyotas.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("porsche"))
				{
				  allporsches = MySqlDataStoreUtilities.getPorsches();
				  if(!allporsches.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("lincoln"))
				{
				  alllincolns = MySqlDataStoreUtilities.getLincolns();
				  if(!alllincolns.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("headlight"))
				{
				  allheadlights = MySqlDataStoreUtilities.getHeadlights();
				  if(!allheadlights.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("rims"))
				{
				  allrims = MySqlDataStoreUtilities.getRims();
				  if(!allrims.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("windowstints"))
				{
				  allwindowtints = MySqlDataStoreUtilities.getWindowtints();
				  if(!allwindowtints.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("backlights"))
				{
				  allbacklight = MySqlDataStoreUtilities.getBacklights();
				  if(!allbacklight.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("exhaust"))
				{
				  allexhausts = MySqlDataStoreUtilities.getExhausts();
				  if(!allexhausts.containsKey(productId)){
					  msg = "Product not available";
				  }
				}
				else if (producttype.equals("accessories"))
				{
				  allaccessories = MySqlDataStoreUtilities.getAccessories();
				  if(!allaccessories.containsKey(productId)){
					  msg = "Product not available";
				}
			}	
			if (msg.equals("good"))
			{		

			  try
			  {
				msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
			  }
			  catch(Exception e)
			  { 
				msg = "Product cannot be updated";
			  }
			  msg = "Product has been successfully updated";
			} 
			}
			else if(action.equals("delete"))
			{
				msg = "bad";
				allmercedes = MySqlDataStoreUtilities.getMercedes();
				if(allmercedes.containsKey(productId)){
				  msg = "good";
				 
				}
				  

				allbmws = MySqlDataStoreUtilities.getBmws();
				if(allbmws.containsKey(productId)){
				  msg = "good";
				}

				allaudis = MySqlDataStoreUtilities.getAudis();
				if(allaudis.containsKey(productId)){
				  msg = "good";
				}

				alljeeps = MySqlDataStoreUtilities.getJeeps();
				if(alljeeps.containsKey(productId)){
				  msg = "good";
				}

				allhyundais = MySqlDataStoreUtilities.getHyundais();
				if(allhyundais.containsKey(productId)){
				  msg = "good";
				}
				alltoyotas = MySqlDataStoreUtilities.getToyotas();
				if(alltoyotas.containsKey(productId)){
				  msg = "good";
				}
				allporsches = MySqlDataStoreUtilities.getPorsches();
				if(allporsches.containsKey(productId)){
				  msg = "good";
				}
				alllincolns = MySqlDataStoreUtilities.getLincolns();
				if(alllincolns.containsKey(productId)){
				  msg = "good";
				}
				allheadlights = MySqlDataStoreUtilities.getHeadlights();
				if(allheadlights.containsKey(productId)){
				  msg = "good";
				}
				allrims = MySqlDataStoreUtilities.getRims();
				if(allrims.containsKey(productId)){
				  msg = "good";
				}
				allwindowtints = MySqlDataStoreUtilities.getWindowtints();
				if(allwindowtints.containsKey(productId)){
				  msg = "good";
				}
				allbacklight = MySqlDataStoreUtilities.getBacklights();
				if(allbacklight.containsKey(productId)){
				  msg = "good";
				}
				allexhausts = MySqlDataStoreUtilities.getExhausts();
				if(allexhausts.containsKey(productId)){
				  msg = "good";
				}
				allaccessories = MySqlDataStoreUtilities.getAccessories();
				if(allaccessories.containsKey(productId)){
					  msg = "good";
				}		       		
				if (msg.equals("good"))
				{		
				  try
				  {  					
					 msg = MySqlDataStoreUtilities.deleteproducts(productId);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be deleted";
				  }
				   msg = "Product has been successfully deleted";
				}else{
				  msg = "Product not available";
				}
			}				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");			
	}
}