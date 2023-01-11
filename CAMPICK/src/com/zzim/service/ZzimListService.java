package com.zzim.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tag.model.TagSearchDto;

public interface ZzimListService {
	
	public ArrayList<TagSearchDto> execute(HttpServletRequest request, HttpServletResponse response);


}
