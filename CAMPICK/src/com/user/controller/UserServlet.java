package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.model.UserDao;
import com.user.model.UserDto;
import com.user.service.IDCheckService;
import com.user.service.IDCheckServicelmpl;
import com.user.service.LoginService;
import com.user.service.LoginServicelmpl;
import com.user.service.SearchIdService;
import com.user.service.SearchIdServicelmpl;
import com.user.service.SearchPwService;
import com.user.service.SearchPwServicelmpl;
import com.user.service.UnregisterService;
import com.user.service.UnregisterServicelmpl;
import com.user.service.UserService;
import com.user.service.UserServicelmpl;

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
		
//패스워드, id 찾기---------------------------------------------------------------
		
		if(action.equals("searchID")) {
			System.out.println("searchid 들어옴~");
			
			String s_name = request.getParameter("s_name");
			String tel = request.getParameter("s_tel");
			String s_tel = tel.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");

			  SearchIdService searchIdServlet = new SearchIdServicelmpl();
			  String searchUser = searchIdServlet.execute(s_name, s_tel);
			  
			  PrintWriter out = response.getWriter(); if(searchUser!=null) {
			  request.setAttribute("searchUser", searchUser);
			  
			  
			  RequestDispatcher requestDispatcher =
			  request.getRequestDispatcher("/searchId.jsp");
			  requestDispatcher.forward(request, response);
			  
			  } else { out.println
			  ("<script>alert ('이름 또는 번호를 다시 확인해 주세요.'); location.href='searchId.jsp'; </script>"
			  ); out.close ();
			  }
			
		}
		
		
		if(action.equals("searchPW")){
			System.out.println("search PW 들어옴~");

			String s_id = request.getParameter("s_id");
			String s_email = request.getParameter("s_email");
			String s_tel = request.getParameter("s_tel");
			
			SearchPwService searchpw = new SearchPwServicelmpl();
			
			String searchPw = searchpw.execute(s_id, s_email, s_tel);
			
			  PrintWriter out = response.getWriter();
			  if(searchPw!=null) {
			  request.setAttribute("searchPw", searchPw);
			 
			  RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/searchPw.jsp");
			  requestDispatcher.forward(request, response);
			  
			  } else {
			  out.println ("<script>alert ('해당 회원이 없습니다. 정보를 다시 확인해 주세요.'); location.href='searchPw.jsp'; </script>");
			  out.close ();
			  }
			  
		}
		
		
		if(action.equals("unregister")) {
			System.out.println("회원 탈퇴 들어옴!!");

			HttpSession session = request.getSession();
			String deletID = (String) session.getAttribute("deletID");

			request.setAttribute("deletID", deletID);
			
			UnregisterService unregisterService = new UnregisterServicelmpl();
			
			unregisterService.execute(request, response);
			session.invalidate();
			response.sendRedirect("search.jsp");
		}
		
		if(action.contentEquals("checkID")) {
			System.out.println("idcheck 들어옴");
			String checkID = request.getParameter("user_id");
			System.out.println(checkID);
			request.setAttribute("checkID", checkID);
			
			IDCheckService idCheckService = new IDCheckServicelmpl();
			
			int result = (int)idCheckService.execute(request, response);
			//request.setAttribute("result", result);
			
			//  RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/regist.jsp");
			// requestDispatcher.forward(request, response);
		
			  PrintWriter out = response.getWriter();
			  
			  if(result==1) {
				out.println ("<script>alert ('사용할 수 있는 아이디입니다.')</script>");
				request.setAttribute("result", result);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/idCheck.jsp");
				requestDispatcher.forward(request, response);
				}else {
				out.println("<script>alert ('사용할 수 없는 아이디입니다.')</script>"); }
			
		}
	}

}
