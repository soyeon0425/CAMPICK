package com.tag.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.borad.model.BoradDto;


public class TagSearchDao {
	
	private static TagSearchDao instance = new TagSearchDao();
	
	public TagSearchDao() {}
	
	public static TagSearchDao getInstance() {
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
	
	  public ArrayList<TagSearchDto> getList(String[] tag, int startRow, int endRow){
	      ArrayList<TagSearchDto> tagSearchList = new ArrayList<>();
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	    	  conn=getConnection();
	    	  
	    /*-------------------------------------or 조건으로 검색	  
	    	  String sql = "select * From camp_info where";
	    	  sql+="(";
	    	  for(int i = 0; i<tag.length; i++) {

	    	  if(tag[i].equals("체험")){
	    		  sql+="exprnprogrm IS NOT NULL";
	    	  }else{
	    		  sql+="intro like '%"+tag[i]+"%'";
	    	  }
	    	  
	    	  if(i+1<tag.length){
	    		  sql+=" or ";
	    	  }else{
	    		  sql+=")";
	    	  }
	    	  
	    	  }
	    	  sql+=" AND addr like '%경기%'";
	    	  
	    	  System.out.println(sql);
		----------------------------------------------------*/
	    	String sql = "SELECT * FROM (SELECT a.*, ROWNUM as rnum FROM (SELECT * FROM camp_info WHERE addr like '%경기%'";
	    	if(tag!=null) {
		    	  for(int i = 0; i<tag.length; i++) {
		    		  if(tag[i].equals("체험")) {
		    			sql+=" AND exprnprogrm IS NOT NULL";
		    		  }else {
		    			  sql+=" AND intro like '%"+tag[i]+"%'";
		    		  }
		    	  }
		    	  }
	    	sql+="ORDER BY camp_id DESC) a)  WHERE rnum >= ? and rnum <=?";
	    	
	    	/*----------------수정 전 기존 sql
	    	  String sql = "select * From camp_info where addr like '%경기%'";
	    	  if(tag!=null) {
	    	  for(int i = 0; i<tag.length; i++) {
	    		  if(tag[i].equals("체험")) {
	    			sql+=" AND exprnprogrm IS NOT NULL";
	    		//	sql+="AND intro like '%반려%' AND intro like '%동물%'";
	    		  }else {
	    			  sql+=" AND intro like '%"+tag[i]+"%'";
	    		  }
	    	  }
	    	  }
	    	  -----------------------*/
		  		System.out.println(sql);
	    	  pstmt = conn.prepareStatement(sql);
	    	  pstmt.setInt(1, startRow);
	    	  pstmt.setInt(2, endRow);

	    	  rs = pstmt.executeQuery();

	    	  while(rs.next()) {
		    	TagSearchDto tto = new TagSearchDto();
		    	tto.setCamp_id(rs.getInt("camp_id"));
		    	tto.setCamp_name(rs.getString("camp_name"));
		    	System.out.println(tto.getCamp_name());
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
//	    		int camp_id = rs.getInt("camp_id");
//	    		String camp_name =rs.getString("camp_name");
//	    		String lineintro = rs.getString("lineintro");
//	    		String intro = rs.getString("intro");
//	    		String facility= rs.getString("facility");
//	    		String place = rs.getString("place");
//	    		String doNm = rs.getString("doNm");
//	    		String sigunguNm = rs.getString("sigunguNm");
//	    		String addr = rs.getString("addr");
//	    		String mapX = rs.getString("mapX");
//	    		String mapY = rs.getString("mapY");
//	    		String tel = rs.getString("tel");
//	    		String homepage = rs.getString("homepage");
//	    		String season = rs.getString("season");
//	    		String operdate = rs.getString("operdate");
//	    		String subplace = rs.getString("subplace");
//	    		String playplace = rs.getString("playplace");
//	    		String thema = rs.getString("thema");
//	    		String eqpmnLendCl = rs.getString("eqpmnLendCl");
//	    		String exprnProgrm = rs.getString("exprnProgrm");

		    	tagSearchList.add(tto);
	    		
	    	  }
	      }catch(SQLException e) {//sql
	    	  e.printStackTrace();
	      }finally{
	          try {
	              rs.close();
	              pstmt.close();
	              conn.close();
	              
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	      return tagSearchList;
	  }
	  
	  
	  public int getDBCount(String[] tag) {
		  Connection conn = null;
	      PreparedStatement pstmt = null;
		  TagSearchDto tagdto = new TagSearchDto();
		  	
    	  String sql = "select count(*) as camp_count From camp_info where addr like '%경기%'";
    	  
    	  if(tag!=null) {
    	  for(int i = 0; i<tag.length; i++) {
    		  if(tag[i].equals("체험")) {
    			sql+="AND exprnprogrm IS NOT NULL";
    		  }else {
    			  sql+="AND intro like '%"+tag[i]+"%'";
    		  }
    	  }
    	  }
    	  System.out.println(sql);

  		try {
  			conn = getConnection();
  			pstmt = conn.prepareStatement(sql);
  			ResultSet rs = pstmt.executeQuery();
  			
  			if(rs.next()) {
  				tagdto.setCamp_count(rs.getInt("camp_count"));
  				rs.close();
  			}
  			System.out.println("dto get 카운트 : "+tagdto.getCamp_count());
  			
  		}catch(SQLException e) {
  			e.printStackTrace();
  		}finally {
	          try {
	              pstmt.close();
	              conn.close();
	              
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
  			}
  		
  		return tagdto.getCamp_count();
	  }
	  
	  public TagSearchDto getCampInfo(int camp_id){
	      
		  TagSearchDto tto = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
		      String sql = "select * From camp_info where camp_id="+camp_id;
		      System.out.println(sql);
	    	  
	    	  conn = getConnection();
		      pstmt = conn.prepareStatement(sql);
	    	  rs = pstmt.executeQuery();
	    	  
	    	  while(rs.next()) {
	    	  	tto = new TagSearchDto();
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
	    	  }
	    	  
	      }catch(SQLException e) {
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
	      
	      return tto;
	      
		  
	  }

}
