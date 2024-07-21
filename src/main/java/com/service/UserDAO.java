package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDAO {

	public boolean insertBike(int id, String bike_model, String bike_number) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			String q="insert into bike (user_id,bike_model,bike_number) values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1,id);
			pst.setString(2,bike_model);
			pst.setString(3,bike_number);
			pst.executeQuery();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public String getBikeDetails(Integer id) {
		// TODO Auto-generated method stub
		try {
			
			List<Bikes> list=new ArrayList<>();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			PreparedStatement  preparedStatement=con.prepareStatement("SELECT bike_id, bike_model, bike_number FROM Bike WHERE user_id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Bikes bike=new Bikes(resultSet.getInt("bike_Id"),resultSet.getString("bike_model"),resultSet.getString("bike_number"));
				list.add(bike);
			}
			ObjectMapper objectMapper=new ObjectMapper();
			String json=objectMapper.writeValueAsString(list);
			System.out.println(json);
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public boolean insertService(Integer userId, int bikeId, String bikeModel, String bikeNumber, String date, String service) {
	    try {
	        String statusString = "pending";
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "sundar");
	        String q = "INSERT INTO service (user_id, service_date, service_description, status, bike_id) VALUES (?, TO_DATE(?), ?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(q);
	        pst.setInt(1, userId);
	        pst.setString(2, date);
	        pst.setString(3, service);
	        pst.setString(4, statusString);
	        pst.setInt(5, bikeId);
	        pst.executeUpdate(); // Use executeUpdate for insert, update, delete
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
