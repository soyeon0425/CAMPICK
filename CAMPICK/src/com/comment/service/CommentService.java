package com.comment.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommentService {
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
