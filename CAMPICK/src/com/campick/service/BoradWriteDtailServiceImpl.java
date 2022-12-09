package com.campick.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;

public class BoradWriteDtailServiceImpl implements BoradWriteDetailService{
	BoradDao dao;
	
	public BoradWriteDtailServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	@Override
	public BoradDto execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		return dao.getDB((int)session.getAttribute("boradid"));
	}

}
