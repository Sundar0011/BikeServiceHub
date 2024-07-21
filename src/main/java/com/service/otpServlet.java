package com.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class otpServlet
 */
public class otpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public otpServlet() {
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
		HttpSession session=request.getSession();
		Signup signup=(Signup) session.getAttribute("authcode");
		String vcode=request.getParameter("vcode");
		if(vcode.equals(signup.getCode()))
		{
			String userName=signup.getName();
			String phone=signup.getPhone();
			String Password=signup.getPassword();
			String Email=signup.getEmail();
			SignupDAO db=new SignupDAO();
			if(db.insert(userName,phone,Email,Password))
			{
				response.getWriter().print("Signup sccessfully");
			}
			else
			{
				response.sendRedirect("Data not inserted");
			}
		}
		else
		{
			response.getWriter().print("Invalid code!!!!");
		}
	}

}
