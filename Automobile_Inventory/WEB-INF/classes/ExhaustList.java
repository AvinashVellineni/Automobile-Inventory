import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExhaustList")

/* ExhaustList java file displays all the cars with manufacturer as Exhaust */

public class ExhaustList extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		

		/* Get automobiles with manufacturer as audi from MySQL database */

		HashMap<String,Exhaust> allexhausts = new HashMap<String,Exhaust> ();		
		try{
		     allexhausts = MySqlDataStoreUtilities.getExhausts();
		}
		catch(Exception e)
		{
			
		}
		String name = null;
		String CategoryName = "Exhaust";
		HashMap<String, Exhaust> hm = new HashMap<String, Exhaust>();
		if (true)	
		{
			hm.putAll(allexhausts);
			name = "";
		} 
		
		/* Header, Left Navigation Bar are Printed.

		All Exhausts and information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Exhausts</a>");
		pw.print("</h2><div class='entry'><table>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Exhaust> entry : hm.entrySet()) {
			Exhaust exhaust = entry.getValue();
			if (i % 1 == 1)
				pw.print("<div class='cscontentst'>");
				pw.print("<tr style='border: solid;border-width: 2px 0;'>");
				pw.print("<td style='font-family: Helvetica Neue,Helvetica,Arial,sans-serif;font-size: 14px;font-weight: bold;font-color: black !important;color: #d80a14;'>"+exhaust.getName()+"</td>");
				pw.print("<td id='item'><img  class='pic' src='images/exhaust/"
					+ exhaust.getImage() + "' alt='' /><img class='picbig' src='images/exhaust/"
					+ exhaust.getImage() + "' alt='' /></td>");
				pw.print("<td style='font-family: initial;font-size: 14px;'>$" + exhaust.getPrice() + "</td>");
				pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='exhaust'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<td style='padding-left: 30px;'><input type='submit' class='btnbuy' value='Buy Now'></td></form>");
				pw.print("<form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='exhaust'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+exhaust.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<td style='padding-left: 10px;'><input type='submit' value='WriteReview' class='btnreview'></td></form>");
				pw.print("<form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='exhaust'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<td style='padding-left: 10px;'><input type='submit' value='ViewReview' class='btnreview'></td></form>");
			if (i % 1 == 0 || i == size)
				pw.print("</tr>");
				
			i++;
			pw.print("</div>");
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
