import java.io.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;

class MyComparator implements Comparator<String>
    {
        public int compare(String o1,String o2)
        {
           return (o1.compareTo(o2));
        }
    }

@WebServlet("/SalesReport")

/* Displays the Sales details to the Storemanager */

public class SalesReport extends HttpServlet {
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("ProductAvailability"))
		{			
			Map<OrderItem,String> map = new HashMap<OrderItem,String>();
			// Get all products sold from MySQl database
			map = MySqlDataStoreUtilities.getAllProductSold();
			if(map!=null){
				pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Sold</a></h2>"
						+ "<div class='entry'>");
				if(map.isEmpty()){
	              pw.print("<h4 style='color:red'>No Product sold details are available..</h4>");
	            }
	            else{
					pw.print("<table border='2' style='width:80%;Margin-top: 30px'>");
					pw.print("<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Product Name</th><th style='font-size:14px;font-weight: bold;text-align:center'>Product Price</th><th style='font-size:14px;font-weight: bold;text-align:center'>Items Sold</th><th style='font-size:14px;font-weight: bold;text-align:center'>Total Sales</th></tr>");
					Iterator it = map.entrySet().iterator();
					while(it.hasNext())
					{
						Map.Entry entry = (Map.Entry) it.next();
						OrderItem key = (OrderItem) entry.getKey();
						String value = (String) entry.getValue();
						pw.println("<tr><td style='font-size:12px;text-align:center'>" +key.getName()+ " </td><td style='font-size:12px;text-align:center'>$" +key.getPrice()+ "</td><td style='font-size:12px;text-align:center'>" +value+ "</td><td style='font-size:12px;text-align:center'>$" + key.getPrice() * Float.parseFloat(value) + "</td></tr>");
					}
					pw.print("</table>");
				}
				pw.print("</div></div></div>");
			}
			else{
				pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Products Sold</a></h2>"
						+ "<div class='entry'>");
				pw.print("<h4 style='color:red'>MySQL server is Down..</h4>");
				pw.print("</div></div></div>");
			}
		}
		//Graphical view of ProductName on Yaxis and Total sales on Xaxis.
		else if (action.equals("GraphicalView"))
		{
				pw.print("<div id='content'><div class='post'>");
				pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Graphical View Of Products Sold</a></h2>"
						+ "<div class='entry'>");
		   		pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		   		pw.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
							"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
		   		pw.println("<script type='text/javascript'>");
				Map<OrderItem,String> map1 = new HashMap<OrderItem,String>();
				map1 = MySqlDataStoreUtilities.getAllProductSold();
				pw.println("google.charts.load('current', {'packages':['corechart']});");
				pw.println("google.charts.setOnLoadCallback(drawChart);");
				pw.println("function drawChart() {");
				pw.println("var data = new google.visualization.DataTable();");
				pw.println("data.addColumn('string', 'Product Name');");
				pw.println("data.addColumn('number', 'Total Sales');");
			   	pw.println(" data.addRows([");
				Iterator it = map1.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry entry = (Map.Entry) it.next();
					OrderItem key = (OrderItem) entry.getKey();
					String value = (String) entry.getValue();
					Double value1=Double.parseDouble(value)*key.getPrice();
					String value2= value1+"";
					pw.println(" ['"+key.getName()+"', "+value2+"],");
				}
				pw.println("]);");
				// Set chart options
				pw.println(" var options = {'title':'Items Sold',");
				pw.println("        'width':600,");
				pw.println("       'height':650};");	 
				// Instantiate and draw our chart, passing in some options.
				pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
				pw.println("  chart.draw(data, options);     }");
				pw.println(" </script>");
				//Draw chart
		 		pw.println("<div id='chart_div'></div>");
		   		pw.print("</div></div></div>");
		}
		// Total Sales by Date
		else if (action.equals("SalesByDate"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report By Date</a></h2>"
				+ "<div class='entry'>");
				TreeMap<String, Float> dailySales = new TreeMap<String , Float>(new MyComparator());
				dailySales = MySqlDataStoreUtilities.getDailySales();
				pw.println("<table border='2' style='width:80%;Margin-top: 30px'>"+
					"<tr style='background-color: #14e01b'><th style='font-size:14px;font-weight: bold;text-align:center'>Date </th><th style='font-size:14px;font-weight: bold;text-align:center'>Total Sales</th></tr>");
				Iterator it = dailySales.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry entry = (Map.Entry) it.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					double d = Double.parseDouble(value);
					DecimalFormat df = new DecimalFormat("#.####");
					pw.println("<tr><td style='font-size:12px;text-align:center'>" +key+ " </td><td style='font-size:12px;text-align:center'>$" +df.format(d)+ "</td></tr>");
				}
				pw.println("</table>");
			pw.print("</div></div></div>");
		}
		else{
			pw.print("");
		}
		utility.printHtml("Footer.html");
		}
	}