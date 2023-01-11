package com.tag.model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.user.model.UserDao;

public class TagImgDao {

	private static TagImgDao instance = new TagImgDao();
	
	public TagImgDao() {}
	
	public static TagImgDao getInstance() {
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
	
}
