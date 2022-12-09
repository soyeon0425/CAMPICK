package com.campick.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDao {
	
	private static UserDao instance = new UserDao();
	
	public UserDao() {}
	
	public static UserDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
	      Context context = null;
	      DataSource dataSource = null;
	      Connection connection = null;
	      
	      
	      try {
	         context = new InitialContext();
	         dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/Oracle11g");
	         connection = dataSource.getConnection();
	         System.out.println("getConnection 되었습니다!!");
	      } catch (Exception e) {
	         System.out.println("getConnection()예외");
	         e.printStackTrace();
	      }
	      return connection;
	}
	
	public void register(UserDto user) {
		System.out.println("dao까지 넘어옴~~");
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {

	          conn = getConnection();
	          pstmt = conn.prepareStatement("INSERT INTO USER_TB VALUES(?, ?, ?, ?, ?, ?)");
	          pstmt.setString(1, user.getId());
	          pstmt.setString(2, user.getPw());
	          pstmt.setString(3, user.getName());
	          pstmt.setString(4, user.getPhone());
	          pstmt.setString(5, user.getEmail());
	          pstmt.setString(6, user.getAddr());
	          
	          pstmt.executeUpdate();
	          
	      
	      }catch(SQLException e) {
	    	  System.out.println("register 오류!!");
	    	  e.printStackTrace();
	      }finally {
	    	  try {
	    		  pstmt.close();
	    		  conn.close();
	    	  }catch(Exception e2) {
	    		  e2.printStackTrace();
	    	  }
	      }
		
	}
	
	
	
}
