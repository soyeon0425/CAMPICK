package com.campick.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.UserDto;

public interface LoginService {
	
	public UserDto execute(String id, String pw);
}
