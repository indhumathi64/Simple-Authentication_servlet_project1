package com.sap.Implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sap.Bean.RegisterBean;
import com.sap.DatabaseConnection.DatabaseConnection;
import com.sap.Interface.Sinterface;


public class SImplementation implements Sinterface {
	Connection con;


	public int register(RegisterBean rb) {
		int result = 0;
		con = DatabaseConnection.createConnection();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO register VALUES(?,?,?,?,?,?)");
			ps.setInt(1, rb.getId());
			ps.setString(2, rb.getName());
			ps.setString(3, rb.getUsername());
			ps.setString(4, rb.getPassword());
			ps.setString(5, rb.getEmail());
			ps.setString(6, rb.getMobile());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public int login(String username, String password) {
		int result = 0;
		con = DatabaseConnection.createConnection();
		try{
		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM `register`WHERE uname='"+username+"' and pass= '"+password+"' ");
		    System.out.println("The username is: "+username);
		    System.out.println("The password is: "+password);
		    
		    while(rs.next()){
		    	String usernamech = rs.getString("uname");
		    	String passwordch = rs.getString("pass");
		    	
		    	if(username.equals(usernamech)&&password.equals(passwordch)){
		    		result=1;
		    		
		    	}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}

