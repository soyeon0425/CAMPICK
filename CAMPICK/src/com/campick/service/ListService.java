package com.campick.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.BoradDto;

public interface ListService {
	public ArrayList<BoradDto> execute(HttpServletRequest request, HttpServletResponse response);
}
