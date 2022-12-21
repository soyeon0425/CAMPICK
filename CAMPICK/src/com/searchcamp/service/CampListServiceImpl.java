package com.searchcamp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.searchcamp.model.SearchCampDao;
import com.searchcamp.model.SearchCampDto;

public class CampListServiceImpl implements CampListService{

	SearchCampDao scDao;
	
	public CampListServiceImpl() {
		scDao = SearchCampDao.getInstance();
	}
	
	@Override
	public ArrayList<SearchCampDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String camp_name = request.getParameter("camp_name");
		String cdo = request.getParameter("cdo");
		String sigungu = request.getParameter("sigungu");
		String[] camptype = request.getParameterValues("camptype");
		return scDao.getDBList(camp_name, cdo, sigungu, camptype);
	}

}
