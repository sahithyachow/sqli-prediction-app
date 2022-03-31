package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
public class SQLInjection extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	try{
		String query = request.getParameter("t1").trim();
		String path = getServletContext().getRealPath("/")+"WEB-INF/dataset/template.txt";
		query = GenerateVector.getVector(path,query);
		path = getServletContext().getRealPath("/")+"WEB-INF/dataset/test.txt";
		FileOutputStream fout = new FileOutputStream(path);
		byte data[] = query.getBytes();
		fout.write(data,0,data.length);
		fout.close();
		String output = LogisticAlgorithm.test(path);
		System.out.println(output);
		//HttpSession session=request.getSession();
		//session.setAttribute("output",output);
		RequestDispatcher rd=request.getRequestDispatcher("User.jsp?t1="+output);
		rd.forward(request, response);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
