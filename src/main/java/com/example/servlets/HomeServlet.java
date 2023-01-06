package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.models.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(
		urlPatterns = "/home", 
		initParams = { 
				@WebInitParam(name = "firstName", value = "John"),
				@WebInitParam(name = "lastName", value = "Doe")
				
				
				}
		)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
//		out.println("<html>");
//		out.println("<body>");
//		out.println("<h1> hello servlet </h1>");
//		out.println("</body>");
//		out.println("<html>");
		
		//parametre de requete
		// homefirstName=John
		//String firstName = request.getParameter("firstName");
		//out.print("Hello" + firstName);
		
		
		String firstName = getInitParameter("firstName");
	    String lastName = getInitParameter("lastName");
	  
	     
	    
	    request.setAttribute("firstName", firstName);
	    request.setAttribute("lastName", lastName);
	   
	    
		
		String sport = "Football";
		request.setAttribute("sport", sport);
		
		User user = new User("John", "Doe", 2000);
		request.setAttribute("user", user);
		
		List<User> users = new ArrayList<>();
		users.add(new User("John", "Doe", 2000));
		users.add(new User("John", "Wayne", 2000));
		users.add(new User("John", "Wick", 2000));
		users.add(new User("Diane", "Wick", 2000));
		request.setAttribute("users", users);
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp")
		.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
