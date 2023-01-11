package com.tag.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tag.model.TagSearchDao;
import com.tag.model.TagSearchDto;

public class TagDetailServicelmpl implements TagDetailService {
	

	private TagSearchDao tagSearchDao;
	
	public TagDetailServicelmpl(){
		tagSearchDao = TagSearchDao.getInstance();
		
	}

	public TagSearchDto execute(HttpServletRequest request, HttpServletResponse response) throws SQLException{

		int camp_id = Integer.parseInt(request.getParameter("camp_id"));
		System.out.println("service에 들어온 camp_id"+camp_id);
		return tagSearchDao.getCampInfo(camp_id);
	}

}
