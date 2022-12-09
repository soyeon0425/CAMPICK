package com.campick.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;

public class ListServiceImpl implements ListService{
	BoradDao dao;
	public ListServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	@Override
	public ArrayList<BoradDto> execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return dao.getDBList();
	}
}