package com.campick.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.UserDto;
import com.campick.service.LoginService;
import com.campick.service.LoginServicelmpl;
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
			
			String loginId = request.getParameter("userid");
			String loginPw = request.getParameter("password");
			
			System.out.println(loginId);
			System.out.println(loginPw);
			
			LoginService loginService = new LoginServicelmpl();
			UserDto loginUser = loginService.execute(loginId, loginPw);
			
		//로그인할 객체가 없으면 null. 있으면 로그인 세션값에 담은 후 search.jsp

			PrintWriter out = response.getWriter();
			if(loginUser!=null) {
			System.out.println("로그인 성공!");
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			UserDto User = (UserDto)session.getAttribute("loginUser");
			
			System.out.println("로그인한 사람은 "+User.getId());
			System.out.println("로그인한 사람 메일은 "+User.getEmail());
			System.out.println("로그인한 사람 이름은 "+User.getName());

			
			out.println ("<script>alert ('환영합니다!'); location.href='index.jsp'; </script>"); 
			out.close ();

			//response.sendRedirect("index.jsp");
		
			}else {
				System.out.println("로그인 실패!");
				out.println ("<script>alert ('비밀번호 또는 아이디를 다시 입력해주세요'); location.href='login.jsp'; </script>"); 
				out.close ();
			}
		}
		
		if(action.equals("logout")) {
			System.out.println("로그아웃 들어옴~");

			HttpSession session= request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		
		
	}

}
