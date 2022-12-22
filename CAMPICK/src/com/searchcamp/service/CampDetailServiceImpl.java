package com.searchcamp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.searchcamp.model.SearchCampDao;
import com.searchcamp.model.SearchCampDto;

public class CampDetailServiceImpl implements CampDetailService{
	SearchCampDao scDao;
	
	public CampDetailServiceImpl() {
		scDao = SearchCampDao.getInstance();
	}
	@Override
	public SearchCampDto execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return scDao.getDB(Integer.parseInt(request.getParameter("camp_id")));
	}
}
