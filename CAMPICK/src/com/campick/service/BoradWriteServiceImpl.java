package com.campick.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;

public class BoradWriteServiceImpl implements BoradWriteService{
	BoradDao dao;
	
	public BoradWriteServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		return dao.insertBorad((BoradDto)request.getAttribute("dto"));
	}
	
}
