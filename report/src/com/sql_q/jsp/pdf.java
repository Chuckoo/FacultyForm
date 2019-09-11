package com.sql_q.jsp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pdf")
public class pdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String button = request.getParameter("button");
		switch(button) {
		case "button0":
			retrieve.getPDF("testpdf1", "C:/Users/VK/Desktop/testpdf1");
			break;
		case "button1":
			retrieve.getPDF("testpdf2", "C:/Users/VK/Desktop/testpdf2");
			break;
		case "button2":
			retrieve.getPDF("testpdf3", "C:/Users/VK/Desktop/testpdf3");
			break;
		case "button3":
			retrieve.getPDF("testpdf4", "C:/Users/VK/Desktop/testpdf4");
			break;
		}
		
		request.getRequestDispatcher("/hod.jsp").forward(request, response);*/
	}

}
