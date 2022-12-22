package com.searchcamp.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SearchCampDao {
	
	private static SearchCampDao instance = new SearchCampDao();
	
	private SearchCampDao() {
		
	}
	
	public static SearchCampDao getInstance() {
		return instance;
	}

	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public ArrayList<SearchCampDto> getDBList(String camp_name,String donm, String sigungu,String[] camptype, int startRow, int pageSize) throws Exception{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ArrayList<SearchCampDto> scDtoList = new ArrayList<>();
		
		String sql = "select * from (select rownum rn , a.* from (select b.* from camp_info b where "; //sql문
		
		//캠핑장명 입력 있으면 camp_name 입력 없으면 빈공간으로 해서 sql에  추가
		if(camp_name != null) {	sql +="camp_name Like '%"+camp_name+"%' AND ";}
		else {sql +="camp_name Like '%%' AND ";}
		
		//지역에 시/도 입력값 있으면 cdo 추가 없으면 %경기도%로 sql 추가
		if(donm != null) {sql +="donm Like '%경기%' AND ";}
		else {sql +="donm Like '%경기도%' AND ";}
		
		// 시/군/구 입력값 있으면 sigungu , 없으면 빈공간으로 sql문 추가
		if(sigungu != null) {sql +="sigungunm Like '%"+sigungu+"%' AND ";}
		else {sql +="sigungunm Like '%%' AND ";}
		
		if(camptype != null) {
			//camptype 배열 값 만큼 돌리고 각 배열  value값을 sql문에 저장
			for(int i=0; i<camptype.length;i++) {
				sql += "facility LIKE '%"+camptype[i]+"%'";
				if(i+1 == camptype.length) {
					break; //뒤에 OR 안찍히게 하기위한 조건문
				}
				sql += " AND ";
			}
		}else {sql += "facility Like '%%'";}
		
		sql +=" )a ) where rn>=? and rn <=?";
		
		System.out.println("sql문: "+sql);
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow+pageSize-1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchCampDto scDto = new SearchCampDto();
				scDto.setCamp_id(rs.getInt("camp_id"));
				scDto.setCamp_name(rs.getString("camp_name"));
				scDto.setAddr(rs.getString("addr"));
				scDto.setTel(rs.getString("tel"));
				scDto.setSubPlace(rs.getString("subplace"));
				scDto.setLineIntro(rs.getString("lineintro"));
				scDtoList.add(scDto);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				pstmt.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return scDtoList;
	}
	public int getDBcount(String camp_name,String donm, String sigungu,String[] camptype) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		SearchCampDto scDto = new SearchCampDto();
		
		String sql = "SELECT count(*) as camp_count from camp_info where ";
		if(camp_name != null) {	sql +="camp_name Like '%"+camp_name+"%' AND ";}
		else {sql +="camp_name Like '%%' AND ";}
		
		//지역에 시/도 입력값 있으면 cdo 추가 없으면 %경기도%로 sql 추가
		if(donm != null) {sql +="donm Like '%경기%' AND ";}
		else {sql +="donm Like '%경기도%' AND ";}
		
		// 시/군/구 입력값 있으면 sigungu , 없으면 빈공간으로 sql문 추가
		if(sigungu != null) {sql +="sigungunm Like '%"+sigungu+"%' AND ";}
		else {sql +="sigungunm Like '%%' AND ";}
		if(camptype != null) {
			//camptype 배열 값 만큼 돌리고 각 배열  value값을 sql문에 저장
			for(int i=0; i<camptype.length;i++) {
				sql += "facility LIKE '%"+camptype[i]+"%'";
				if(i+1 == camptype.length) {
					break; //뒤에 OR 안찍히게 하기위한 조건문
				}
				sql += " AND ";
			}
		}else {sql += "facility Like '%%'";}
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scDto.setCamp_count(rs.getInt("camp_count"));
				rs.close();
			}
			System.out.println("dto get 카운트 : "+scDto.getCamp_count());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		
		return scDto.getCamp_count();
	}
	
}
