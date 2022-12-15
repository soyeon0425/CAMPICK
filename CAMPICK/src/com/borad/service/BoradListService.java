package com.borad.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.borad.model.BoradDto;

public interface BoradListService {
	public ArrayList<BoradDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
