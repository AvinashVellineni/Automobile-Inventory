import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/ProductData")

/* ProductData java file is used to display the selected product when selected using the autocomplete search feature */

public class ProductData extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			PrintWriter pw= response.getWriter();
			response.setContentType("text/html");			
			pw.println("<html>");
			pw.println("<body>");
			Utilities utility = new Utilities(request,pw);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Product Selected</a></h2><div class='entry'><table id='bestseller'>");
			pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			Product data= (Product)request.getAttribute("data");
			String  type1=null;
			if(data.getType().equals("mercedes")){
				type1="mercedes";
			}else if(data.getType().equals("bmw")){
				type1="bmw";
			}else if(data.getType().equals("audi")){
				type1="audi";
			}else if(data.getType().equals("jeep")){
				type1="jeep";
			}else if(data.getType().equals("hyundai")){
				type1="hyundai";
			}else if(data.getType().equals("toyota")){
				type1="toyota";
			}else if(data.getType().equals("porsche")){
				type1="porsche";
			}else if(data.getType().equals("lincoln")){
				type1="lincoln";
			}else if(data.getType().equals("headlight")){
				type1="headlight";
			}else if(data.getType().equals("rims")){
				type1="rims";
			}else if(data.getType().equals("windowtint")){
				type1="windowtint";
			}else if(data.getType().equals("backlight")){
				type1="backlight";
			}else if(data.getType().equals("exhaust")){
				type1="exhaust";
			}else if(data.getType().equals("accessories")){
				type1="accessories";
			}
			pw.print("<h3>"+data.getName()+"</h3>");
			pw.print("<strong>$"+data.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/"+type1+"/"+data.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+data.getId()+"'>"+
					"<input type='hidden' name='type' value='"+data.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+
			"<input type='hidden' name='name' value='"+data.getId()+"'>"+
					"<input type='hidden' name='type' value='"+data.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    	"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+
					"<input type='hidden' name='name' value='"+data.getId()+"'>"+
					"<input type='hidden' name='type' value='"+data.getType()+"'>"+
					"<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+		
					"<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");		
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
			utility.printHtml("Footer.html");
		}
		catch(Exception e)
		{
			
		}
	}	
	public void destroy()	{

	}
}