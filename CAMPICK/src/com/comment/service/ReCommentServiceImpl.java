package com.comment.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.UserDto;
import com.comment.model.CommentDao;
import com.comment.model.CommentDto;

public class ReCommentServiceImpl implements ReCommentService{
	CommentDao cDao;
	
	public ReCommentServiceImpl() {
		cDao = CommentDao.getInstance();
	}
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		UserDto uDto = (UserDto)session.getAttribute("loginUser");
		return cDao.insertReComment((CommentDto)request.getAttribute("cDto"), (int)session.getAttribute("boradid"), uDto.getName());
	}

}
