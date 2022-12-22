package com.getimg.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getimg.model.GetImgDao;
import com.getimg.model.GetImgDto;

public class GetImgListServiceImpl implements GetImgListService{
	GetImgDao giDao;
	
	public GetImgListServiceImpl() {
		giDao = GetImgDao.getInstance();
	}
	@Override
	public GetImgDto execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return giDao.getDB(Integer.parseInt(request.getParameter("camp_id")));
	}

}
