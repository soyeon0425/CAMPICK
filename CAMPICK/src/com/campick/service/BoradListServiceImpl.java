package com.campick.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;

public class BoradListServiceImpl implements BoradListService{
	BoradDao dao;
	public BoradListServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	//List를 보여주는 메소드
	@Override
	public ArrayList<BoradDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getDBList();
	}
}
