package com.comment.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.CommentDto;


public interface CommentListService {
	public ArrayList<CommentDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
