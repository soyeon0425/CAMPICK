package com.borad.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borad.model.BoradDao;
import com.borad.model.BoradDto;

public class BoradWriteDtailServiceImpl implements BoradWriteDetailService{
	BoradDao dao;
	
	public BoradWriteDtailServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	//글 디테일 페이지 보여주는 메소드
	@Override
	public BoradDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int boradId = (int)session.getAttribute("boradid");
		BoradDto dto = dao.getDB(boradId);
		dao.increaseVisit(boradId);
		return dto;
	}

}
