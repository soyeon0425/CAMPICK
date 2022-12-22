package com.searchcamp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.searchcamp.model.SearchCampDto;

public interface CampListService {
	public ArrayList<SearchCampDto> execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
