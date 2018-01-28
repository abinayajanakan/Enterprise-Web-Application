import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LogoutServlet extends HttpServlet
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		
		if(session != null)
		{
			session.invalidate();
		}
		
		RequestDispatcher view = request.getRequestDispatcher("HomeServlet");
		view.forward(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	
}