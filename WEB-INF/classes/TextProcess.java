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
public class TextProcess extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	try{
		String dataset = getServletContext().getRealPath("/")+"WEB-INF/dataset/dataset.txt";
		String process = getServletContext().getRealPath("/")+"WEB-INF/dataset/dataset.arff";
		File file = new File(process);
		GenerateVector.generateVector(dataset);
		if(!file.exists()){
			Convert.convert(dataset,process);
		}
		RequestDispatcher rd=request.getRequestDispatcher("TextProcess.jsp?t1=Text preprocessing completed");
		rd.forward(request, response);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}

}
