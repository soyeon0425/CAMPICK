package com.campick.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.UserDao;
import com.campick.model.UserDto;

public class UserServicelmpl implements UserService{
	private UserDao userDao;
	
	public UserServicelmpl() {
		userDao=UserDao.getInstance();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("service까지 넘어옴!!");
		UserDto regUser = (UserDto)request.getAttribute("regUser");
		userDao.register(regUser);
	}
}
