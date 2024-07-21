package com.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ServiceServlet
 */
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceServlet() {
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
		Integer userId=(Integer)session.getAttribute("userId");
		int bikeId=Integer.parseInt(request.getParameter("bikeId"));
		String bikeModel=request.getParameter("bikeModel");
		String bikeNumber=request.getParameter("bikeNumber");
		String date=request.getParameter("date");
		String service=request.getParameter("service");
		UserDAO userDAO=new UserDAO();
		String[] d=date.split("-");
		String cDateString="";
		String ownerMail="sundaravel.s2021@nandhatech.org";
		String msg="user_id:"+userId+" BikeModel:"+bikeModel+" Bike Number:"+bikeNumber+" Service Date:"+date+" Service Type:"+service;
		for(int i=d.length-1;i>=0;i--)
		{
			if(i==d.length-1)
				cDateString+=d[i];
			else {
				cDateString+="-"+d[i];
			}
		}
		System.out.println(cDateString);
		if(userDAO.insertService(userId,bikeId,bikeModel,bikeNumber,cDateString,service)) {
			ServiceMail serviceMail=new ServiceMail();
			if(serviceMail.sendmail(ownerMail,msg)) {
				response.getWriter().print("mail Send");
				
			}
			else {
				response.getWriter().print("mail not Send");
			}
		}
		else {
			response.getWriter().print("notadded");
		}
	}

}
