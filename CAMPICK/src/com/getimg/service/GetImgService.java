package com.getimg.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getimg.model.GetImgDto;

public interface GetImgService {
	public ArrayList<GetImgDto> execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
