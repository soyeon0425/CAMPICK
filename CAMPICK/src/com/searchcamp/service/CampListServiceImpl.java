package com.searchcamp.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.searchcamp.model.SearchCampDao;
import com.searchcamp.model.SearchCampDto;

public class CampListServiceImpl implements CampListService{

	SearchCampDao scDao;
	
	public CampListServiceImpl() {
		scDao = SearchCampDao.getInstance();
	}
	
	@Override
	public ArrayList<SearchCampDto> execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String camp_name = request.getParameter("camp_name");
		String donm = request.getParameter("donm");
		String sigungu = request.getParameter("sigungu");
		String[] camptype = null;
		if(request.getParameterValues("camptype") != null) {
			camptype = request.getParameterValues("camptype");
		}
		if(request.getParameter("camptypes") != null) {
			String camptypes = request.getParameter("camptypes");
			camptype = camptypes.split(",");
		}
		
		session.setAttribute("camp_name", camp_name);
		session.setAttribute("donm", donm);
		session.setAttribute("sigungu", sigungu);
		session.setAttribute("camptype", camptype);
		
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
