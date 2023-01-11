package com.zzim.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzim.model.ZzimDao;

public class ZzimPlusServicelmpl implements ZzimPlusService {

	private ZzimDao zzimDao;
	
	public ZzimPlusServicelmpl() {
		
		zzimDao = ZzimDao.getInstance();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String user_id=request.getParameter("user_id");
	    System.out.println(user_id);
	    int camp_id=Integer.parseInt(request.getParameter("camp_id"));
	    System.out.println(camp_id);
		zzimDao.ZzimPlus(user_id, camp_id);
		
	}
}
