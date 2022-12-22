package com.getimg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GetImgDao {
	
private static GetImgDao instance = new GetImgDao();
	
	private GetImgDao() {
		
	}
	
	public static GetImgDao getInstance() {
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
	
	public ArrayList<GetImgDto> getImgDBList() throws Exception{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ArrayList<GetImgDto> giDtoList = new ArrayList<>();
		
		String sql = "select * from camp_img"; //sql문
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GetImgDto giDto = new GetImgDto();
				
				giDto.setCamp_id(rs.getInt("camp_id"));
				giDto.setImgUrl1(rs.getString("imgUrl1"));
				giDto.setImgUrl2(rs.getString("imgUrl2"));
				giDto.setImgUrl3(rs.getString("imgUrl3"));
				giDtoList.add(giDto);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				pstmt.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return giDtoList;
	}
	
	public GetImgDto getDB(int camp_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		GetImgDto giDto = new GetImgDto();
		
		String sql = "SELECT * from camp_img where camp_id ="+camp_id;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				giDto.setImgUrl1(rs.getString("imgUrl1"));
				giDto.setImgUrl2(rs.getString("imgUrl2"));
				giDto.setImgUrl3(rs.getString("imgUrl3"));
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		
		return giDto;
	}
}
