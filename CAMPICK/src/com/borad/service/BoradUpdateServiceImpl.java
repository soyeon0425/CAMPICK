package com.borad.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borad.model.BoradDao;
import com.borad.model.BoradDto;

public class BoradUpdateServiceImpl implements BoradUpdateService{
	BoradDao dao;
	
	public BoradUpdateServiceImpl() {
		dao = BoradDao.getInstance();
	}
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		return dao.updateDB((int)session.getAttribute("boradid"), (BoradDto)request.getAttribute("dto"));
	}

}
