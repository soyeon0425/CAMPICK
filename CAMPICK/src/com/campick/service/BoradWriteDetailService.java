package com.campick.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.BoradDto;

public interface BoradWriteDetailService {
	public BoradDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
