package com.campick.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.model.UserDto;
import com.campick.service.UserService;
import com.campick.service.UserServicelmpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("user.do 들어옴!!!!!");
		
		String action=request.getParameter("action");
		
		
		if(action.equals("register")) {
			System.out.println("Register 들어옴!!!!!");
		
			UserDto regUser = new UserDto();
			
			regUser.setId(request.getParameter("user_id"));
			regUser.setPw(request.getParameter("user_pw"));
			regUser.setName(request.getParameter("user_name"));
			regUser.setPhone(request.getParameter("user_tel"));
			regUser.setEmail(request.getParameter("user_email"));
			regUser.setAddr(request.getParameter("user_addr"));
			

			request.setAttribute("regUser", regUser);
			UserService userService = new UserServicelmpl();
			userService.execute(request, response);
			response.sendRedirect("login.jsp");
			//response.sendRedirect("main.do");
		}
		
		if(action.equals("login")){
			System.out.println("로그인페이지 들어옴~");
			
		}
		
		
	}

}
