package com.searchcamp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.searchcamp.model.SearchCampDto;

public interface CampDetailService {
	public SearchCampDto execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
