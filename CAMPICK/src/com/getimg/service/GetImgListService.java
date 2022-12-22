package com.getimg.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getimg.model.GetImgDto;

public interface GetImgListService {
	public GetImgDto execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
