package com.suggest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borad.model.BoradDao;
import com.suggest.model.SuggestDao;
import com.suggest.model.SuggestDto;
import com.user.model.UserDto;

public class SuggestServiceImpl implements SuggestService{
	SuggestDao sDao;
	BoradDao bDao;
	public SuggestServiceImpl() {
		sDao = SuggestDao.getInstance();
		bDao = BoradDao.getInstance();
	}
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		UserDto uDto = (UserDto)hs.getAttribute("loginUser");
		int borad_id = (int)hs.getAttribute("boradid");
		SuggestDto sDto = sDao.checkDB(borad_id, uDto.getId(), uDto.getName());
		
		
		if(sDto.getChecked() == 0){
			sDao.insertDB(borad_id, uDto.getId(), uDto.getName());
			sDto = sDao.checkDB(borad_id, uDto.getId(), uDto.getName());
		}
		if(sDto.getChecked() == 2){
			bDao.increaseSuggest(borad_id);
			sDao.updateChecked(1, borad_id, uDto.getId(), uDto.getName());
			return 1;
		}else{
			bDao.decreaseSuggest(borad_id);
			sDao.updateChecked(2, borad_id, uDto.getId(), uDto.getName());
			return 2;
		}
	}
}
