package com.tag.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tag.model.TagSearchDto;

public interface TagDetailService {
	

	public TagSearchDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;



}
