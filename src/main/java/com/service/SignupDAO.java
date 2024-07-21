package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupDAO {
	public boolean check(String email) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			PreparedStatement preparedStatement=con.prepareStatement("select * from bikeuser");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				String e=resultSet.getString("email");
				if(e.equals(email))
					return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public boolean insert(String name, String phone, String email, String password) {
		Connection con=null;
		boolean result=false;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
		String q="insert into bikeuser (name, phone, email, password) values(?,?,?,?)";
		PreparedStatement pst=con.prepareStatement(q);
		pst.setString(1,name);
		pst.setString(2,phone);
		pst.setString(3,email);
		pst.setString(4,password);
		
		ResultSet rs=pst.executeQuery();
		result =rs.next();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result;
		}
		
		return result;
	}

	public boolean checklogin(String email) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from bikeuser");
			while(rs.next())
			{
				
				String e=rs.getString("Email");
				if(e.equals(email))
				{
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public int fecthUserId(String email) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			PreparedStatement preparedStatement=con.prepareStatement("select * from bikeuser where email=?");
			preparedStatement.setString(1, email);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				int userId = rs.getInt("user_id");
				return userId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
