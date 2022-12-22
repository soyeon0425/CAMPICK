package com.searchcamp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.searchcamp.model.SearchCampDao;
import com.searchcamp.model.SearchCampDto;

public class CampListServiceImpl implements CampListService{

	SearchCampDao scDao;
	
	public CampListServiceImpl() {
		scDao = SearchCampDao.getInstance();
	}
	
	@Override
	public ArrayList<SearchCampDto> execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String camp_name = request.getParameter("camp_name");
		String donm = request.getParameter("donm");
		String sigungu = request.getParameter("sigungu");
		String[] camptype = request.getParameterValues("camptype");
		
		request.setAttribute("camp_count", scDao.getDBcount(camp_name, donm, sigungu, camptype));
		int pageSize = 9;
    	String pageNo = request.getParameter("page");
    	if(pageNo == null){
    		pageNo = "1";
    	}
    	int curPage = Integer.parseInt(pageNo);
    	int startRow = ((curPage-1) * pageSize) +1 ;
		
		return scDao.getDBList(camp_name, donm, sigungu, camptype,startRow,pageSize);
	}
}
