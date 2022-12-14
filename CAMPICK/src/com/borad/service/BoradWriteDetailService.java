package com.borad.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borad.model.BoradDto;

public interface BoradWriteDetailService {
	public BoradDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
