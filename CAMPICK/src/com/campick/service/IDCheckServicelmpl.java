package com.campick.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.UserDao;

public class IDCheckServicelmpl implements IDCheckService {

	private UserDao userDao;
	
	public IDCheckServicelmpl() {
		userDao = UserDao.getInstance();
	}
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String checkID = (String) request.getAttribute("checkID");
//		System.out.println(checkID);
		int result = userDao.idCheck(checkID);
		return result;
	}
}
