package com.campick.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDao;
import com.campick.model.BoradDto;
import com.campick.model.UserDto;

public class BoradWriteServiceImpl implements BoradWriteService{
	BoradDao dao;
	
	public BoradWriteServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = BoradDao.getInstance();
	}
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("writeServiceImpl문 getName은"+userDto.getName());
		return dao.insertBorad((BoradDto)request.getAttribute("dto"),userDto.getName());
	}
	
}
