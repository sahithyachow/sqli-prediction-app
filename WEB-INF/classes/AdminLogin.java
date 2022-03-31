package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AdminLogin extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session=request.getSession();
	PrintWriter out = response.getWriter();
	String user=request.getParameter("t1");
	String pass=request.getParameter("t2");
	try{
		if(user.equals("admin") && pass.equals("admin")){
			RequestDispatcher rd=request.getRequestDispatcher("AdminScreen.jsp?t1=Welcome "+user);
			rd.forward(request, response);
		}else{
			response.sendRedirect("Login.jsp?t1=Invalid User");
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
