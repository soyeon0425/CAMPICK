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
		String camp_name = request.getParameter("camp_name");
		String donm = request.getParameter("donm");
		String sigungu = request.getParameter("sigungu");
		String[] camptype = null;
		String[] place = null;
		String[] thema = null;
		String[] subplace = null;
		
		//캠프타입
		if(request.getParameterValues("camptype") != null) {
			camptype = request.getParameterValues("camptype");
		}else {
			camptype = (String[]) request.getAttribute("camptype");
		}
		if(request.getParameter("camptypes") != null) {
			String camptypes = request.getParameter("camptypes");
			camptype = camptypes.split(",");
		}
		//place 타입
		if(request.getParameterValues("place") != null) {
			place = request.getParameterValues("place");
		}else {
			place = (String[]) request.getAttribute("place");
		}
		if(request.getParameter("camptypes") != null) {
			String places = request.getParameter("places");
			place = places.split(",");
		}
		//thema 타입
		if(request.getParameterValues("thema") != null) {
			thema = request.getParameterValues("thema");
		}else {
			thema = (String[]) request.getAttribute("thema");
		}
		if(request.getParameter("themas") != null) {
			String themas = request.getParameter("themas");
			thema = themas.split(",");
		}
		//subplace 타입
		if(request.getParameterValues("subplace") != null) {
			subplace = request.getParameterValues("subplace");
		}else {
			subplace = (String[]) request.getAttribute("subplace");
		}
		if(request.getParameter("subplaces") != null) {
			String subplaces = request.getParameter("subplaces");
			subplace = subplaces.split(",");
		}
		
		request.setAttribute("camp_name", camp_name);
		request.setAttribute("donm", donm);
		request.setAttribute("sigungu", sigungu);
		request.setAttribute("camptype", camptype);
		request.setAttribute("place", place);
		request.setAttribute("thema", thema);
		request.setAttribute("subplace", subplace);
		
		request.setAttribute("camp_count", scDao.getDBcount(camp_name, donm, sigungu, camptype, place, thema, subplace));
		
		int pageSize = 9;
    	String pageNo = request.getParameter("page");
    	if(pageNo == null){
    		pageNo = "1";
    	}
    	int curPage = Integer.parseInt(pageNo);
    	int startRow = ((curPage-1) * pageSize) +1 ;
		
		return scDao.getDBList(camp_name, donm, sigungu, camptype, place, thema, subplace,startRow,pageSize);
	}
}
