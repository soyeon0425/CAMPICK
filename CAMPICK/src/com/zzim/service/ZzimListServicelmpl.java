package com.zzim.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tag.model.TagSearchDao;
import com.tag.model.TagSearchDto;
import com.zzim.model.ZzimDao;

public class ZzimListServicelmpl implements ZzimListService{
	
	private ZzimDao zzimDao;
	
	public ZzimListServicelmpl() {
		zzimDao = ZzimDao.getInstance();
	}
	

	@Override

	public ArrayList<TagSearchDto> execute(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		String ID = (String) session.getAttribute("deletID");
		
		ArrayList<TagSearchDto> myList = zzimDao.getMyList(ID);
		return myList;
	}


}
