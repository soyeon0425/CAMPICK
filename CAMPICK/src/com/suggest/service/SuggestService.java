package com.suggest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuggestService {
	public int execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
