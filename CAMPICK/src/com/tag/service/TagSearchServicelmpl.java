package com.tag.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tag.model.TagSearchDao;
import com.tag.model.TagSearchDto;

public class TagSearchServicelmpl implements TagSearchService{
	
	private TagSearchDao tagSearchDao;
	
	public TagSearchServicelmpl(){
		tagSearchDao = TagSearchDao.getInstance();
		
	}
	
	@Override
	
	public ArrayList<TagSearchDto> execute(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		
		String[] tag = null;
		
		if(request.getParameterValues("tag")!=null) {
			tag = request.getParameterValues("tag");
		}
		if(request.getParameter("retag")!=null){
			String tags = request.getParameter("retag");
			System.out.println(tags);
			tag = tags.split(",");
			System.out.println(Arrays.toString(tag));
		}
		
		//String[] tag = request.getParameterValues("tag");
		//String[] tag = (String[]) request.getAttribute("tag");
		System.out.println("service로 넘어온 tag는 "+Arrays.toString(tag));

		request.setAttribute("tag", tag);
		
		int camp_count = tagSearchDao.getDBCount(tag);
		request.setAttribute("camp_count", camp_count);

		int pageSize = 3;
		
		//현재 페이지 pageNum
		String pageNum = request.getParameter("page");
		System.out.println("pageNum은 "+pageNum);
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = startRow+(pageSize-1);
		return tagSearchDao.getList(tag, startRow, endRow);
		
	}

	

}
