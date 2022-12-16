package com.suggest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SuggestDao {
	private static SuggestDao instance = new SuggestDao();
	public static final int SUGGEST_ON = 1;
	public static final int SUGGEST_OFF = 2;
	private SuggestDao() {
		// TODO Auto-generated constructor stub
	}

	public static SuggestDao getInstance() {
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
	public boolean insertDB(int borad_id, String id , String name) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		//DB에 insert할 sql문
		String sql = "insert into borad_suggest values (sug_seq.nextval,?,?,?,"+SUGGEST_OFF+")";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, borad_id);//borad_id 내용
			pstmt.setString(2, id);//reply 댓글 내용
			pstmt.setString(3, name);//reply 댓글 내용
			pstmt.executeUpdate();
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
	public SuggestDto checkDB(int borad_id, String id , String name) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		SuggestDto sDto = new SuggestDto();
		//DB에 insert할 sql문
		String sql = "SELECT checked from borad_suggest where borad_id = ? AND id=? AND name=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, borad_id);//borad_id 내용
			pstmt.setString(2, id);//reply 댓글 내용
			pstmt.setString(3, name);//reply 댓글 내용
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				sDto.setChecked(rs.getInt("checked"));
				rs.close();
			}
			System.out.println("check된 값"+sDto.getChecked());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
		return sDto;
	}
	
	public boolean updateChecked(int checked,int borad_id,String id, String name) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "update borad_suggest set checked = ? where borad_id=? AND id=? AND name =?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, checked);//borad_id 내용
			pstmt.setInt(2, borad_id);//borad_id 내용
			pstmt.setString(3, id);//borad_id 내용
			pstmt.setString(4, name);//borad_id 내용
			pstmt.executeUpdate();
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
}
