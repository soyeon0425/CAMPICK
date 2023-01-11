package com.zzim.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzim.model.ZzimDao;

public class ZzimCheckServicelmpl implements  ZzimCheckService{
	
	private ZzimDao zzimDao;
	
	public ZzimCheckServicelmpl() {
		zzimDao = ZzimDao.getInstance();
	}
	

	public int execute(HttpServletRequest request, HttpServletResponse response){
		String user_id=request.getParameter("user_id");
	    System.out.println(user_id);
	    int camp_id=Integer.parseInt(request.getParameter("camp_id"));
	    System.out.println(camp_id);
		int count =zzimDao.zzimCheck(user_id, camp_id);
		
		return count;
		
	}

	
	

}
