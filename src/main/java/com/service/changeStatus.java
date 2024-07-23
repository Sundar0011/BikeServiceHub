package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changeStatus
 */
public class changeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeStatus() {
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
		String bikeId=request.getParameter("bikeId");
		String status=request.getParameter("status");
		UserDAO userDAO=new UserDAO();
		String jsonString="[{response:success}]";
		if(userDAO.updateStatus(bikeId,status));{
			 response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        PrintWriter out = response.getWriter();
		        out.print(jsonString);
		        out.flush();
		}
		if(status.equals("Delivery")) {
	
			String email=userDAO.getEmail(bikeId);
			ServiceMail serviceMail=new ServiceMail();
			String Msg="Your bike Was Ready To Delivery.";
			serviceMail.sendmail(email, Msg);
			System.out.println("mail Send");
		}
		if(status.equals("pending")) {
			String email=userDAO.getEmail(bikeId);
			ServiceMail serviceMail=new ServiceMail();
			String Msg="Your slot is confirm.";
			serviceMail.sendmail(email, Msg);
			
			System.out.println("confirm mail send");
		}
		if(status.equals("cancel")) {
			String email=userDAO.getEmail(bikeId);
			ServiceMail serviceMail=new ServiceMail();
			String Msg="Sorry Slot was not available at this date. so select another date";
			serviceMail.sendmail(email, Msg);
		
		}
				
	}

}
