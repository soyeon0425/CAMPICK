package com.zzim.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ZzimCheckService {

	public int execute(HttpServletRequest request, HttpServletResponse response);
}
