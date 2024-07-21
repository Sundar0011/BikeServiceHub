package com.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String Email=request.getParameter("Email");
		String Password=request.getParameter("Password");
		String name="";
		String code="";
		String phone="";
		Signup signup=new Signup(name,phone,Email,Password,code);
		SignupDAO signupDAO=new SignupDAO();
		if(signupDAO.checklogin(Email))
		{
			int id=signupDAO.fecthUserId(Email);
			HttpSession session=request.getSession();
			session.setAttribute("userId",id);
			response.sendRedirect("UserHome.html");
		}
		else
		{
			response.getWriter().print("Not signing");
		}
	}

}
