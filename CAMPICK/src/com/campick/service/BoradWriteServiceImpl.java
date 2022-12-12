package com.campick.service;

import java.sql.SQLException;

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
	//게시판 등록 메소드
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		return dao.insertBorad((BoradDto)request.getAttribute("dto"),userDto.getName());
	}
	
}
