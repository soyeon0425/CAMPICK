package com.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public UserDto login(String loginId, String loginPw) {
		System.out.println("로그인 dao까지 넘어옴~");
		System.out.println(loginId);
		
		UserDto loginUser = null;
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
		
		try {
			conn=getConnection();
			pstmt= conn.prepareStatement("SELECT * FROM USER_TB WHERE ID=? AND PW=?");
			pstmt.setString(1, loginId); //비워둔 부분에 받아온 id를 넣음
			pstmt.setString(2, loginPw); //비워둔 부분에 받아온 pw를 넣음
			rs = pstmt.executeQuery(); //그 결과를 rs에 저장
			
			
			rs.next();
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String name = rs.getString("name");
			String addr = rs.getString("addr");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			   
			loginUser=new UserDto(id, pw, name, addr, phone, email);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
//				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return loginUser;
	}
	
	
	public String SearchID(String s_name, String s_tel) {
		System.out.println("searchId DAo 넘어옴~");

		//UserDto searchUser = null;
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String id = null;
	    
	    try{
	    	conn = getConnection();
	    	pstmt = conn.prepareStatement("select id FROM USER_TB WHERE name=? AND phone=?");
	    	pstmt.setString(1, s_name);
	    	pstmt.setString(2, s_tel);
	    	rs = pstmt.executeQuery();
	    	
	    	rs.next();
	    	id = rs.getString("id");
			   
	    } catch(SQLException e){
	    	e.printStackTrace();
	    }finally {
	    	try {
	    	rs.close();
	    	pstmt.close();
	    	conn.close();
	    	
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
		return id;
	}
	

	public String SearchPW(String s_id, String s_email, String s_tel) {
		System.out.println("searchpw DAO 넘어옴~");

		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String pw = null;
	    
	    try{
	    	conn = getConnection();
	    	pstmt = conn.prepareStatement("select pw FROM USER_TB WHERE id=? AND email=? AND phone=?");
	    	pstmt.setString(1, s_id);
	    	pstmt.setString(2, s_email);
	    	pstmt.setString(3, s_tel);
	    	
	    	rs = pstmt.executeQuery();
	    	
	    	rs.next();
	    	pw = rs.getString("pw");
			   
	    } catch(SQLException e){
	    	e.printStackTrace();
	    }finally {
	    	try {
	    	rs.close();
	    	pstmt.close();
	    	conn.close();
	    	
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
		return pw;
	}
	
	
	public void unregister(String deletID) {
		
		Connection conn = null;
	    PreparedStatement pstmt = null;

		try{
	    	conn = getConnection();
	        System.out.println("넘어온 delete id는"+deletID);
	    	pstmt = conn.prepareStatement("DELETE FROM USER_TB WHERE id="+"'"+deletID+"'");
	    	pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int idCheck(String checkID) {

		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs= null;
	    int result = 0;
	    
	    try {
	    	conn = getConnection();
	        System.out.println("넘어온 id는"+checkID);
	        pstmt=conn.prepareStatement("select id FROM USER_TB WHERE id=?");
	        pstmt.setString(1, checkID);
	        
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()){
	        	result = 0;
	        }else {
	        	result = 1;
	        }
	    	
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return result;
	}
}
