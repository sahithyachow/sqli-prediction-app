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
public class GenerateModel extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	try{
		String process = getServletContext().getRealPath("/")+"WEB-INF/dataset/dataset.arff";
		String output = LogisticAlgorithm.train(process);
		HttpSession session=request.getSession();
		session.setAttribute("output",GenerateVector.vector_data);
		session.setAttribute("output1",output);
		RequestDispatcher rd=request.getRequestDispatcher("GenerateModel.jsp?t1=Text preprocessing completed");
		rd.forward(request, response);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
