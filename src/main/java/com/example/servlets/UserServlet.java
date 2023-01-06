package com.example.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.impl.UserDaoImpl;
import com.example.models.User;
import com.example.utils.MyConnection;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MyConnection myConnection;
	private UserDaoImpl userDaoImpl;
	private List<User> users;
   
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init() {
		myConnection = new MyConnection();
		userDaoImpl = new UserDaoImpl(myConnection);
		users = new ArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
			try {
				users = userDaoImpl.findAll();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		request.setAttribute("users", users);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users.jsp")
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
