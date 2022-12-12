package com.campick.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDao;

public class BoradDeleteServiceImpl implements BoradDeleteService{
	private BoradDao dao;
	
	public BoradDeleteServiceImpl() {
		dao = BoradDao.getInstance();
	}
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		return dao.deleteDB((int)session.getAttribute("boradid"));
	}

}
