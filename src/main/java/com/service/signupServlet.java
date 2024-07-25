package com.service;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class signupServlet
 */
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupServlet() {
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
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		SignupDAO signupDAO=new SignupDAO();
		if(signupDAO.check(email)) {
			Random random=new Random();
			int num=random.nextInt(99999);
			String code=String.format("%o6", num);
			Signup signup=new Signup(name, phone, email, password,code);
			SendMail sendMail=new SendMail();
			if(sendMail.sendmail(signup)) {
				HttpSession session=request.getSession();
				session.setAttribute("authcode", signup);
				response.sendRedirect("verify.html");
			}
			else {
				response.getWriter().print("<h1>Enter valid user name Password</h1>");
			}
		}
		else {
			response.getWriter().print("<h1>Already taken</h1>");
			
		}
		
	}

}
