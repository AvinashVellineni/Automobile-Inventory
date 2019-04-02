import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")

/* Logout.java file used to logout from the application whenever the user wishes todo so. Logout button is displayed in the hader section the application */

public class Logout extends HttpServlet {

	/*
		logout Function of Utilities class is Called to Logout the User.
	*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilities utility = new Utilities(request, null);
		utility.logout();
		response.sendRedirect("Home");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilities utility = new Utilities(request, null);
		utility.logout();
		response.sendRedirect("Home");
	}
}
