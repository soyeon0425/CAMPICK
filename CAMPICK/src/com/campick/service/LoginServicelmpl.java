package com.campick.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.UserDao;
import com.campick.model.UserDto;

public class LoginServicelmpl implements LoginService{
	
	private UserDao userDao;
	
	public LoginServicelmpl() {
		userDao=UserDao.getInstance();
	}
	@Override
	public UserDto execute(String loginId, String loginPw){
		UserDto loginUser = userDao.login(loginId, loginPw);
		return loginUser;
	}
}
