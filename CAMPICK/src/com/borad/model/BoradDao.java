package com.borad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class BoradDao {
	public static final int BORAD_INSERT_SUCCESS = 1;
	private static BoradDao instance = new BoradDao();
	
	private BoradDao() {
		// TODO Auto-generated constructor stub
	}

	public static BoradDao getInstance() {
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
	//게시판에 글 등록
	public boolean insertBorad(BoradDto dto,String name) throws SQLException {
//		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		//DB에 insert할 sql문
		String sql = "insert into borad(borad_id,borad_visit,borad_suggestion,borad_date,camp_name,"
				+ "borad_period_first,borad_period_second,borad_name,name,borad_text,borad_img)"
				+ " values (borad_seq.nextval,0,0,to_char(sysdate,'yyyy.mm.dd hh24:mi'),?,?,?,?,?,?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dto.getCamp_name());
			pstmt.setString(2, dto.getBorad_period_first());
			pstmt.setString(3, dto.getBorad_period_second());
			pstmt.setString(4, dto.getBorad_name());
			pstmt.setString(5, name);
			pstmt.setString(6, dto.getBorad_text());
			pstmt.setString(7, dto.getBorad_img());
			pstmt.executeUpdate();
			System.out.println(dto.getCamp_name());
//			ri = BORAD_INSERT_SUCCESS;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}finally {
				connection.close();
				pstmt.close();
			}
		}
		return true;
	}
	//게시판 작성 글 목록을 불러오는 메소드
	public ArrayList<BoradDto> getDBList() throws SQLException{
		
		ArrayList<BoradDto> boradList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "select * from borad order by borad_id desc";
		
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoradDto dto = new BoradDto();
				
				dto.setBorad_id(rs.getInt("borad_id"));
				dto.setBorad_name(rs.getString("borad_name"));
				dto.setName(rs.getString("name"));
				dto.setBorad_date(rs.getString("borad_date"));
				dto.setBorad_img(rs.getString("borad_img"));
				boradList.add(dto);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		return boradList;
	}
	
	//게시판 글 상세 페이지 정보를 불러오는 메세지
	public BoradDto getDB(int borad_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "select borad_id,borad_visit,borad_suggestion,borad_date,camp_name,to_char(borad_period_first,'YYYY-MM-DD') as \"borad_period_first\",to_char(borad_period_second,'YYYY-MM-DD')as \"borad_period_second\",borad_name,name,borad_text,borad_img from borad where borad_id=?";
		BoradDto dto = new BoradDto();
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, borad_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			dto.setBorad_id(rs.getInt("borad_id"));
			dto.setBorad_visit(rs.getInt("borad_visit"));
			dto.setBorad_suggestion(rs.getInt("borad_suggestion"));
			dto.setBorad_date(rs.getString("borad_date"));
			dto.setCamp_name(rs.getString("camp_name"));
			dto.setBorad_period_first(rs.getString("borad_period_first"));
			dto.setBorad_period_second(rs.getString("borad_period_second"));
			dto.setBorad_name(rs.getString("borad_name"));
			dto.setName(rs.getString("name"));
			dto.setBorad_text(rs.getString("borad_text"));
			dto.setBorad_img(rs.getString("borad_img"));
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		return dto;
	}
	//게시판 조회수 올려주는 메소드
	public void increaseVisit(int borad_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update borad set borad_visit=borad_visit+1 where borad_id="+borad_id;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
	}
	//게시판 수정 메소드
	public boolean updateDB(int id,BoradDto boradDto) throws SQLException {
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "update borad set borad_name=?,camp_name=?,borad_period_first=?,borad_period_second=?,borad_text=?,borad_img=? where borad_id="+id;
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,boradDto.getBorad_name());
			pstmt.setString(2,boradDto.getCamp_name());
			pstmt.setString(3,boradDto.getBorad_period_first());
			pstmt.setString(4,boradDto.getBorad_period_second());
			pstmt.setString(5,boradDto.getBorad_text());
			pstmt.setString(6,boradDto.getBorad_img());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			connection.close();
			pstmt.close();
		}
		return true;
	}
	//게시판 삭제 메소드
	public boolean deleteDB(int borad_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from borad where borad_id="+borad_id;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		return true;
	}
}