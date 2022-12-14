package com.campick.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.borad.model.BoradDto;
import com.borad.service.BoradDeleteService;
import com.borad.service.BoradDeleteServiceImpl;
import com.borad.service.BoradListService;
import com.borad.service.BoradListServiceImpl;
import com.borad.service.BoradUpdateService;
import com.borad.service.BoradUpdateServiceImpl;
import com.borad.service.BoradWriteDetailService;
import com.borad.service.BoradWriteDtailServiceImpl;
import com.borad.service.BoradWriteService;
import com.borad.service.BoradWriteServiceImpl;
import com.comment.model.CommentDto;
import com.comment.service.CommentListService;
import com.comment.service.CommentListServiceImpl;
import com.comment.service.CommentService;
import com.comment.service.CommentServiceImpl;
import com.comment.service.ReCommentService;
import com.comment.service.ReCommentServiceImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
 * Servlet implementation class CampickServlet
 */
@WebServlet("*.do")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			actionDo(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			actionDo(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("/main.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
			rd.forward(request, response);
		//게시판 리스트 메소드
		}else if(command.equals("/boradList.do")) {
			System.out.println("list 진입");
			BoradListService boradListService = new BoradListServiceImpl();
			ArrayList<BoradDto> boradList= boradListService.execute(request, response);
			request.setAttribute("boradList", boradList);
			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
			rd.forward(request, response);
			
		//글쓰기 화면에서 db로 입력
		}else if(command.equals("/boradInsert.do")){
			String saveFolder = "C:\\Project_camp\\CAMPICK\\CAMPICK\\WebContent\\image";		//사진을 저장할 경로
			String encType = "utf-8";				//변환형식
			int maxSize=5*1024*1024;				//사진의 size
			
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			
			BoradDto dto = new BoradDto();
			dto.setBorad_name(multi.getParameter("borad_name"));
			dto.setCamp_name(multi.getParameter("camp_name"));
			dto.setBorad_period_first(multi.getParameter("borad_period_first"));
			dto.setBorad_period_second(multi.getParameter("borad_period_second"));
			dto.setBorad_text(multi.getParameter("borad_text"));
			dto.setBorad_img(multi.getFilesystemName("borad_img"));
			
			request.setAttribute("dto", dto);
			
			BoradWriteService bws = new BoradWriteServiceImpl();
			bws.execute(request, response);
			response.sendRedirect("boradList.do");
			
		//커뮤니티 글 상세 화면으로 이동
		}else if(command.equals("/boradDetail.do")) {
			System.out.println("detail 진입");
			HttpSession session= request.getSession();
			session.setAttribute("boradid", Integer.parseInt(request.getParameter("borad_id")));
			BoradWriteDetailService bwds = new BoradWriteDtailServiceImpl();
			bwds.execute(request, response);
			
			CommentListService cls = new CommentListServiceImpl();
			ArrayList<CommentDto> commentList = cls.execute(request, response);
			request.setAttribute("commentList", commentList);
			
			RequestDispatcher rd = request.getRequestDispatcher("writeDetail.jsp");
			rd.forward(request, response);

		//수정화면으로 이동
		}else if(command.equals("boradEdit.do")) {
			System.out.println("edit 진입");
			RequestDispatcher rd = request.getRequestDispatcher("writeEdit.jsp");
			rd.forward(request, response);
			
		//수정 데이터 DB 변경	
		}else if(command.equals("/boradUpdate.do")) {
			System.out.println("update 진입");
			
			String saveFolder = request.getSession().getServletContext().getRealPath("/image");		//사진을 저장할 경로
			String encType = "utf-8";				//변환형식
			int maxSize=5*1024*1024;				//사진의 size
			
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			
			BoradDto dto = new BoradDto();
			dto.setBorad_name(multi.getParameter("borad_name"));
			dto.setCamp_name(multi.getParameter("camp_name"));
			dto.setBorad_period_first(multi.getParameter("borad_period_first"));
			dto.setBorad_period_second(multi.getParameter("borad_period_second"));
			dto.setBorad_text(multi.getParameter("borad_text"));
			dto.setBorad_img(multi.getFilesystemName("borad_img"));
			
			request.setAttribute("dto", dto);
			
			BoradUpdateService bus = new BoradUpdateServiceImpl();
			bus.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("writeDetail.jsp");
			rd.forward(request, response);
			
		//게시판 삭제
		}else if(command.equals("/boradDelete.do")) {
			System.out.println("delete 진입");
			
			BoradDeleteService bds = new BoradDeleteServiceImpl();
			bds.execute(request, response);
			response.sendRedirect("boradList.do");
		//댓글 입력
		}else if(command.equals("/boradComment.do")) {
			System.out.println("comment 진입");
			
			CommentDto cDto = new CommentDto();
			cDto.setReply(request.getParameter("reply"));
			
			request.setAttribute("cDto", cDto);
			CommentService commentService = new CommentServiceImpl();
			commentService.execute(request, response);
			HttpSession session = request.getSession();
			response.sendRedirect("boradDetail.do?borad_id="+(int)session.getAttribute("boradid"));
		//대댓글 입력 메소드
		}else if(command.equals("/boradRecomment.do")){
			System.out.println("recomment 진입");
			
			CommentDto cDto = new CommentDto();
			cDto.setReply(request.getParameter("reply"));
			
			request.setAttribute("cDto", cDto);
			ReCommentService reCommentService = new ReCommentServiceImpl();
			
			reCommentService.execute(request, response);
			HttpSession session = request.getSession();
			response.sendRedirect("boradDetail.do?borad_id="+(int)session.getAttribute("boradid"));

//-------------------------------------------------------------------------------------------------------------
		//회원가입 메소드
		}else if(command.equals("/userRegister.do")) {
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
		
		//로그인 메소드
		}else if(command.equals("/userLogin.do")){
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
		
		// 로그아웃 메소드
		}else if(command.equals("/userLogout.do")) {
			System.out.println("로그아웃 들어옴~");

			HttpSession session= request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");

		//패스워드, id 찾기---------------------------------------------------------------
		}else if(command.equals("/userSearchID.do")) {
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
		
		//비밀번호 찾기 
		}else if(command.equals("/userSearchPW.do")){
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
			  
		//회원탈퇴 하는 메소드
		}else if(command.equals("/userUnregister.do")) {
			System.out.println("회원 탈퇴 들어옴!!");

			HttpSession session = request.getSession();
			String deletID = (String) session.getAttribute("deletID");

			request.setAttribute("deletID", deletID);
			
			UnregisterService unregisterService = new UnregisterServicelmpl();
			
			unregisterService.execute(request, response);
			session.invalidate();
			response.sendRedirect("search.jsp");
			
		//아이디 체크 메소드
		}else if(command.contentEquals("//userCheckID.do")) {
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
