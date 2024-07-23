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
	        String statusString = "confirm";
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

    public String pendingDetails(String opt) {
        try {
            List<PendingList> list = new ArrayList<>();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "sundar");

            PreparedStatement preparedStatement = con.prepareStatement("SELECT service_date, service_description, bike_id FROM service WHERE status = ?");
            preparedStatement.setString(1, opt);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String serviceDate = resultSet.getString("service_date");
                String serviceDescription = resultSet.getString("service_description");
                int bikeId = resultSet.getInt("bike_id");

                // Fetch bike details using bike_id
                PreparedStatement bikeStmt = con.prepareStatement("SELECT bike_model, bike_number FROM bike WHERE bike_id = ?");
                bikeStmt.setInt(1, bikeId);
                ResultSet bikeResultSet = bikeStmt.executeQuery();

                String bikeModel = "";
                String bikeNumber = "";
                if (bikeResultSet.next()) {
                    bikeModel = bikeResultSet.getString("bike_model");
                    bikeNumber = bikeResultSet.getString("bike_number");
                }

                PendingList pendingList = new PendingList(serviceDate, serviceDescription, bikeId, bikeModel, bikeNumber);
                list.add(pendingList);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public boolean updateStatus(String bikeId, String status) {
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "sundar");

	            PreparedStatement preparedStatement = con.prepareStatement("update service set status=? where bike_id=?");
	            preparedStatement.setString(1, status);
	            preparedStatement.setString(2,bikeId);
	            preparedStatement.executeQuery();
	            return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String readyDetails(String opt) {
        try {
            List<PendingList> list = new ArrayList<>();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "sundar");

            PreparedStatement preparedStatement = con.prepareStatement("SELECT service_date, service_description, bike_id FROM service WHERE status = ?");
            preparedStatement.setString(1, opt);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String serviceDate = resultSet.getString("service_date");
                String serviceDescription = resultSet.getString("service_description");
                int bikeId = resultSet.getInt("bike_id");

                // Fetch bike details using bike_id
                PreparedStatement bikeStmt = con.prepareStatement("SELECT bike_model, bike_number FROM bike WHERE bike_id = ?");
                bikeStmt.setInt(1, bikeId);
                ResultSet bikeResultSet = bikeStmt.executeQuery();

                String bikeModel = "";
                String bikeNumber = "";
                if (bikeResultSet.next()) {
                    bikeModel = bikeResultSet.getString("bike_model");
                    bikeNumber = bikeResultSet.getString("bike_number");
                }

                PendingList pendingList = new PendingList(serviceDate, serviceDescription, bikeId, bikeModel, bikeNumber);
                list.add(pendingList);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            return json;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public String completeDetails(String opt) {
	    try {
            List<PendingList> list = new ArrayList<>();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "sundar");

            PreparedStatement preparedStatement = con.prepareStatement("SELECT service_date, service_description, bike_id FROM service WHERE status = ?");
            preparedStatement.setString(1, opt);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String serviceDate = resultSet.getString("service_date");
                String serviceDescription = resultSet.getString("service_description");
                int bikeId = resultSet.getInt("bike_id");

                // Fetch bike details using bike_id
                PreparedStatement bikeStmt = con.prepareStatement("SELECT bike_model, bike_number FROM bike WHERE bike_id = ?");
                bikeStmt.setInt(1, bikeId);
                ResultSet bikeResultSet = bikeStmt.executeQuery();

                String bikeModel = "";
                String bikeNumber = "";
                if (bikeResultSet.next()) {
                    bikeModel = bikeResultSet.getString("bike_model");
                    bikeNumber = bikeResultSet.getString("bike_number");
                }

                PendingList pendingList = new PendingList(serviceDate, serviceDescription, bikeId, bikeModel, bikeNumber);
                list.add(pendingList);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            return json;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public String getEmail(String bikeId) {
try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","sundar");
			PreparedStatement preparedStatement=con.prepareStatement("select user_id from bike where bike_id=?");
			preparedStatement.setString(1, bikeId);
			ResultSet rs1= preparedStatement.executeQuery();
			rs1.next();
			int userId=Integer.parseInt(rs1.getString("user_id"));
			PreparedStatement preparedStatement2=con.prepareStatement("select email from bikeuser where user_id=?");
			preparedStatement2.setInt(1, userId);
			ResultSet rs=preparedStatement2.executeQuery();
			rs.next();
			String eString=rs.getString("Email");
			return eString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
