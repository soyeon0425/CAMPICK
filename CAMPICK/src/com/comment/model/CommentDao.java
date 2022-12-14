package com.comment.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDao {
	private static CommentDao instance = new CommentDao();
	
	private CommentDao() {
		// TODO Auto-generated constructor stub
	}

	public static CommentDao getInstance() {
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
	
	public boolean insertComment(CommentDto dto,int borad_id,String name) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		//DB에 insert할 sql문
		String sql = "insert into borad_comment values (reply_id_seq.nextval,?,0,bundle_id_seq.nextval,?,?,to_char(sysdate,'yyyy.mm.dd hh24:mi:ss'))";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, borad_id);//borad_id 내용
			pstmt.setString(2, name);//reply 댓글 내용
			pstmt.setString(3, dto.getReply());//reply 댓글 내용
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
public ArrayList<CommentDto> getDBList(int borad_id) throws SQLException{
		
		ArrayList<CommentDto> commentList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * from borad_comment where borad_id="+borad_id+" ORDER BY bundle_id,depth,reply_id";
		
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentDto cDto = new CommentDto();
				cDto.setName(rs.getString("name"));
				cDto.setReply(rs.getString("reply"));
				cDto.setReply_time(rs.getString("reply_time"));
				commentList.add(cDto);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		return commentList;
	}
}
