package com.zzim.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tag.model.TagSearchDto;

public class ZzimDao {
	
	private static ZzimDao instance = new ZzimDao ();
	
	public static ZzimDao getInstance() {
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
	
	
	public void ZzimPlus(String id, int camp_id) {
		
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	   
	      
	      String sql = "Insert Into user_zzim(zzim_num, id, camp_id) VALUES(zzim_seq.nextval, ?, ?)";
	      
	      try {
	    	  conn=getConnection();
	    	  pstmt = conn.prepareStatement(sql);
	    	  pstmt.setString(1, id);
	    	  pstmt.setInt(2, camp_id);
	    	  pstmt.executeUpdate();
	    	  
	      }catch (SQLException e) {
	          e.printStackTrace();
	       }finally {
	          try {
	             pstmt.close();
	             conn.close();
	             
	          } catch (Exception e2) {
	             e2.printStackTrace();
	          }
	       }
	   
		
	}
	
	public int zzimCheck(String id, int camp_id) {
		
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      int count = 0;
	      ResultSet rs = null;
	   
		String sql = "select count(*) from user_zzim where id=? and camp_id=?";
		 
	      try {
	    	  conn=getConnection();
	    	  pstmt = conn.prepareStatement(sql);
	    	  pstmt.setString(1, id);
	    	  pstmt.setInt(2, camp_id);
	    	  rs = pstmt.executeQuery();
	          rs.next();
	          count=rs.getInt(1);
	          rs.close();
	    	 
	    	  
	      }catch (SQLException e) {
	          e.printStackTrace();
	       }finally {
	          try {
	             pstmt.close();
	             conn.close();
	             
	          } catch (Exception e2) {
	             e2.printStackTrace();
	          }
	       }
		return count;
	}
	
	
	public ArrayList<TagSearchDto> getMyList(String id){

		 ArrayList<TagSearchDto> myList = new ArrayList<>();
		
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      
	      try {
	    	  conn=getConnection();
		      String sql = "select * from camp_info where camp_id IN(select camp_id from user_zzim where id=?)";
	    	  pstmt = conn.prepareStatement(sql);
	    	  pstmt.setString(1, id);
	    	  rs = pstmt.executeQuery();
	    	  
	    	  
	    	  while(rs.next()) {
	    		  TagSearchDto tto = new TagSearchDto();
	    		    tto.setCamp_id(rs.getInt("camp_id"));
			    	tto.setCamp_name(rs.getString("camp_name"));
			    	tto.setLineintro(rs.getString("lineintro"));
			    	tto.setIntro(rs.getString("intro"));
			    	tto.setFacility(rs.getString("facility"));
			    	tto.setPlace(rs.getString("place"));
			    	tto.setDoNm(rs.getString("doNm"));
			    	tto.setSigunguNm(rs.getString("sigunguNm"));
			    	tto.setAddr(rs.getString("addr"));
			    	tto.setMapX(rs.getString("mapX"));
			    	tto.setMapY(rs.getString("mapY"));
			    	tto.setTel(rs.getString("tel"));
			    	tto.setHomepage(rs.getString("homepage"));
			    	tto.setSeason(rs.getString("season"));
			    	tto.setSubplace(rs.getString("subplace"));
			    	tto.setPlayplace(rs.getString("playplace"));
			    	tto.setThema(rs.getString("thema"));
			    	tto.setEqpmnLendCl(rs.getString("eqpmnLendCl"));
			    	tto.setExprnProgrm(rs.getString("exprnProgrm"));
			    	myList.add(tto);
	    	  }
	    	  
	    	  
	    	  
	      }catch (SQLException e) {
	          e.printStackTrace();
	       }finally {
	          try {
	             pstmt.close();
	             conn.close();
	             
	          } catch (Exception e2) {
	             e2.printStackTrace();
	          }
	       }
	      
	      return myList;
	}
	
	
}
