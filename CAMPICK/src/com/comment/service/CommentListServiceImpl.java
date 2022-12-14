package com.comment.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comment.model.CommentDao;
import com.comment.model.CommentDto;

public class CommentListServiceImpl implements CommentListService{
	CommentDao cDao;
	
	public CommentListServiceImpl() {
		cDao = CommentDao.getInstance();
	}
	
	@Override
	public ArrayList<CommentDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		return cDao.getDBList((int)session.getAttribute("boradid"));
	}

}
