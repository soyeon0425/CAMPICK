package com.campick.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;

public class BoradEditServiceImpl implements BoradEditService{
	BoradDao dao;
	
	public BoradEditServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	@Override
	public BoradDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		return dao.getWriteDB((int)session.getAttribute("boradid"));
	}

}
