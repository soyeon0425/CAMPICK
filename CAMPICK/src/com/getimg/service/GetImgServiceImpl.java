package com.getimg.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getimg.model.GetImgDao;
import com.getimg.model.GetImgDto;
import com.searchcamp.model.SearchCampDto;

public class GetImgServiceImpl implements GetImgService{
	GetImgDao giDao;
	
	public GetImgServiceImpl() {
		giDao = GetImgDao.getInstance();
	}
	
	@Override
	public ArrayList<GetImgDto> execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return giDao.getImgDBList();
	}

}
