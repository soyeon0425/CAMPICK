package com.campick.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
import com.getimg.model.GetImgDto;
import com.getimg.service.GetImgListService;
import com.getimg.service.GetImgListServiceImpl;
import com.getimg.service.GetImgService;
import com.getimg.service.GetImgServiceImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.searchcamp.model.SearchCampDto;
import com.searchcamp.service.CampDetailService;
import com.searchcamp.service.CampDetailServiceImpl;
import com.searchcamp.service.CampListService;
import com.searchcamp.service.CampListServiceImpl;
import com.suggest.service.SuggestService;
import com.suggest.service.SuggestServiceImpl;
import com.tag.model.TagSearchDto;
import com.tag.service.TagDetailService;
import com.tag.service.TagDetailServicelmpl;
import com.tag.service.TagSearchService;
import com.tag.service.TagSearchServicelmpl;
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
import com.zzim.service.ZzimCheckService;
import com.zzim.service.ZzimCheckServicelmpl;
import com.zzim.service.ZzimListService;
import com.zzim.service.ZzimListServicelmpl;
import com.zzim.service.ZzimPlusService;
import com.zzim.service.ZzimPlusServicelmpl;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("/main.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
			rd.forward(request, response);
		//????????? ????????? ?????????
		}else if(command.equals("/boradList.do")) {
			System.out.println("list ??????");
			BoradListService boradListService = new BoradListServiceImpl();
			ArrayList<BoradDto> boradList= boradListService.execute(request, response);
			request.setAttribute("boradList", boradList);
			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
			rd.forward(request, response);
			
		//????????? ???????????? db??? ??????
		}else if(command.equals("/boradInsert.do")){
			String saveFolder = request.getSession().getServletContext().getRealPath("/image");		//????????? ????????? ??????
			String encType = "utf-8";				//????????????
			int maxSize=5*1024*1024;				//????????? size
			
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
			
		//???????????? ??? ?????? ???????????? ??????
		}else if(command.equals("/boradDetail.do")) {
			System.out.println("detail ??????");
			HttpSession session= request.getSession();
			session.setAttribute("boradid", Integer.parseInt(request.getParameter("borad_id")));
			BoradWriteDetailService bwds = new BoradWriteDtailServiceImpl();
			bwds.execute(request, response);
			
			CommentListService cls = new CommentListServiceImpl();
			ArrayList<CommentDto> commentList = cls.execute(request, response);
			request.setAttribute("commentList", commentList);
			
			RequestDispatcher rd = request.getRequestDispatcher("writeDetail.jsp");
			rd.forward(request, response);

		//?????????????????? ??????
		}else if(command.equals("/boradEdit.do")) {
			System.out.println("edit ??????");
			RequestDispatcher rd = request.getRequestDispatcher("writeEdit.jsp");
			rd.forward(request, response);
			
		//?????? ????????? DB ??????	
		}else if(command.equals("/boradUpdate.do")) {
			System.out.println("update ??????");
			
			String saveFolder = request.getSession().getServletContext().getRealPath("/image");		//????????? ????????? ??????
			String encType = "utf-8";				//????????????
			int maxSize=5*1024*1024;				//????????? size
			
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
			
		//????????? ??????
		}else if(command.equals("/boradDelete.do")) {
			System.out.println("delete ??????");
			
			BoradDeleteService bds = new BoradDeleteServiceImpl();
			bds.execute(request, response);
			response.sendRedirect("boradList.do");
		//?????? ??????
		}else if(command.equals("/boradComment.do")) {
			System.out.println("comment ??????");
			
			CommentDto cDto = new CommentDto();
			cDto.setReply(request.getParameter("reply"));
			
			request.setAttribute("cDto", cDto);
			CommentService commentService = new CommentServiceImpl();
			commentService.execute(request, response);
			HttpSession session = request.getSession();
			response.sendRedirect("boradDetail.do?borad_id="+(int)session.getAttribute("boradid"));
			
		//????????? ?????? ?????????
		}else if(command.equals("/boradRecomment.do")){
			System.out.println("recomment ??????");
			
			CommentDto cDto = new CommentDto();
			cDto.setReply(request.getParameter("reply"));
			cDto.setBundle_id(Integer.parseInt(request.getParameter("bundle_id")));
			request.setAttribute("cDto", cDto);
			ReCommentService reCommentService = new ReCommentServiceImpl();
			
			reCommentService.execute(request, response);
			HttpSession session = request.getSession();
			response.sendRedirect("boradDetail.do?borad_id="+(int)session.getAttribute("boradid"));
			
		//????????? ?????? ??????
		}else if(command.equals("/boradSuggest.do")) {
			SuggestService ss = new SuggestServiceImpl();
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			try {
				if(ss.execute(request, response) ==1) {
					out.println("<script>alert('?????? ???????????????!'); location.href='boradDetail.do?borad_id="+(int)session.getAttribute("boradid")+"';</script>");
					out.flush();
				}else {
					out.println("<script>alert('????????? ?????????????????????!'); location.href='boradDetail.do?borad_id="+(int)session.getAttribute("boradid")+"';</script>");
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------

		//			????????? ?????? ??????!
		}else if(command.equals("/campList.do")) {
			System.out.println("CampList ?????????!");
			
			CampListService cls = new CampListServiceImpl();
			ArrayList<SearchCampDto> scDtoList = cls.execute(request, response);
			request.setAttribute("scDtoList", scDtoList);
			
			GetImgService gis = new GetImgServiceImpl(); 
			ArrayList<GetImgDto> giDtoList = gis.execute(request, response);
			request.setAttribute("giDtoList", giDtoList);
			
			RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
			rd.forward(request, response);
			
		//????????? ?????? ??????
		}else if(command.equals("/campDetail.do")) {
			System.out.println("CampDetail ?????????!");
			
			CampDetailService cds = new CampDetailServiceImpl();
			SearchCampDto scDto = cds.execute(request, response);
			request.setAttribute("scDto", scDto);
			
			GetImgListService gils = new GetImgListServiceImpl();
			GetImgDto giDto = gils.execute(request, response);
			request.setAttribute("giDto", giDto);
			
			RequestDispatcher rd = request.getRequestDispatcher("campDetail.jsp");
			rd.forward(request, response);
			
			
//-------------------------------------------------------------------------------------------------------------
		//???????????? ?????????
		}else if(command.equals("/userRegister.do")) {
			System.out.println("Register ?????????!!!!!");
		
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
		
		//????????? ?????????
		}else if(command.equals("/userLogin.do")){
			System.out.println("?????????????????? ?????????~");
			
			String loginId = request.getParameter("userid");
			String loginPw = request.getParameter("password");
			
			System.out.println(loginId);
			System.out.println(loginPw);
			
			LoginService loginService = new LoginServicelmpl();
			UserDto loginUser = loginService.execute(loginId, loginPw);
			
		//???????????? ????????? ????????? null. ????????? ????????? ???????????? ?????? ??? search.jsp

			PrintWriter out = response.getWriter();
			
			if(loginUser!=null) {
			System.out.println("????????? ??????!");
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			UserDto User = (UserDto)session.getAttribute("loginUser");
			
			System.out.println("???????????? ????????? "+User.getId());
			System.out.println("???????????? ?????? ????????? "+User.getEmail());
			System.out.println("???????????? ?????? ????????? "+User.getName());

			
			out.println ("<script>alert ('???????????????!'); location.href='index.jsp'; </script>"); 
			out.close ();

			//response.sendRedirect("index.jsp");
		
			}else {
				System.out.println("????????? ??????!");
				out.println ("<script>alert ('???????????? ?????? ???????????? ?????? ??????????????????'); location.href='login.jsp'; </script>"); 
				out.close();
			}
		
		// ???????????? ?????????
		}else if(command.equals("/userLogout.do")) {
			System.out.println("???????????? ?????????~");

			HttpSession session= request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");

		//????????????, id ??????---------------------------------------------------------------
		}else if(command.equals("/userSearchID.do")) {
			System.out.println("searchid ?????????~");
			
			String s_name = request.getParameter("s_name");
			String tel = request.getParameter("s_tel");
			String s_tel = tel.replaceAll("[^???-??????-??????-???a-zA-Z0-9]", "");

			  SearchIdService searchIdServlet = new SearchIdServicelmpl();
			  String searchUser = searchIdServlet.execute(s_name, s_tel);
			  
			  PrintWriter out = response.getWriter(); if(searchUser!=null) {
			  request.setAttribute("searchUser", searchUser);
			  
			  
			  RequestDispatcher requestDispatcher =
			  request.getRequestDispatcher("/searchId.jsp");
			  requestDispatcher.forward(request, response);
			  
			  } else { out.println
			  ("<script>alert ('?????? ?????? ????????? ?????? ????????? ?????????.'); location.href='searchId.jsp'; </script>"
			  ); out.close();
			  }
		
		//???????????? ?????? 
		}else if(command.equals("/userSearchPW.do")){
			System.out.println("search PW ?????????~");

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
			  out.println ("<script>alert ('?????? ????????? ????????????. ????????? ?????? ????????? ?????????.'); location.href='searchPw.jsp'; </script>");
			  out.close();
			  }
			  
		//???????????? ?????? ?????????
		}else if(command.equals("/userUnregister.do")) {
			System.out.println("?????? ?????? ?????????!!");

			HttpSession session = request.getSession();
			String deletID = (String) session.getAttribute("deletID");

			request.setAttribute("deletID", deletID);
			
			UnregisterService unregisterService = new UnregisterServicelmpl();
			
			unregisterService.execute(request, response);
			session.invalidate();
			response.sendRedirect("search.jsp");
			
		//????????? ?????? ?????????
		}else if(command.equals("/userCheckID.do")) {
			System.out.println("idcheck ?????????");
			String checkID = request.getParameter("id");
			System.out.println(checkID);
			request.setAttribute("checkID", checkID);
			
			IDCheckService idCheckService = new IDCheckServicelmpl();
			int result = idCheckService.execute(request, response);
			System.out.println("????????? ????????????"+result);
			
			if(result==1){
				RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/regist.jsp");
				requestDispatcher.forward(request, response);
			}else {
				System.out.println("?????? ?????????");
				PrintWriter out = response.getWriter();
				out.println ("<script>alert ('????????? ??? ?????? ??????????????????!'); location.href='regist.jsp'; </script>");
				out.close();

			}
			
		}else if(command.equals("/tagSearch.do")) {
	
	//		String tag = request.getParameter("tag");
	//		String[] tag = request.getParameterValues("tag");
	//		System.out.println(Arrays.toString(tag));
	//		request.setAttribute("tag", tag);

			
			TagSearchService tagService = new TagSearchServicelmpl();
			ArrayList<TagSearchDto> tagSearchList = tagService.execute(request, response);
			
		//	System.out.println("tagSearchList??? ?????? : " + tagSearchList.size());
			request.setAttribute("tagSearchList", tagSearchList);
			RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/tagResult.jsp");
			requestDispatcher.forward(request, response);
			

			//image ????????????
			GetImgService gis = new GetImgServiceImpl(); 
			ArrayList<GetImgDto> giDtoList = gis.execute(request, response);
			request.setAttribute("giDtoList", giDtoList);
			System.out.println("giDtoList??? ?????????"+giDtoList.size());
			

		
		}else if(command.equals("/tagDetail.do")) {
			System.out.println("tag Datail ?????????");
			System.out.println("camp id???"+request.getParameter("camp_id"));
			TagDetailService tagDetailService = new TagDetailServicelmpl();
			TagSearchDto tto = tagDetailService.execute(request, response);
			request.setAttribute("campDetail", tto);
			RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/tagcampDetail.jsp");
			requestDispatcher.forward(request, response);
			
			
			
		}else if(command.equals("/zzim.do")) {
			System.out.println("jjim list ??????");

	//	     String user_id=request.getParameter("user_id");
	//	     System.out.println(user_id);
	//	     int camp_id=Integer.parseInt(request.getParameter("camp_id"));
	//	     System.out.println(camp_id);
		     
		     ZzimCheckService zzimCheck = new ZzimCheckServicelmpl();
		     int count = zzimCheck.execute(request, response);
		     System.out.println(count);
		     PrintWriter out = response.getWriter();
		     
		     if(count==0) {
		    	 ZzimPlusService zzim = new ZzimPlusServicelmpl();
		    	 zzim.execute(request, response);
		    	 out.println ("<script>alert ('??? ????????? ?????????????????????!'); location.href='javascript:history.back();'; </script>");
		     }else{
		    	 out.println ("<script>alert ('?????? ??? ?????? ???????????????.'); location.href='javascript:history.back();'; </script>");
		     
		     }
		     out.close();

		}else if(command.contentEquals("/mypage.do")) {
			//System.out.println("mapage ??????");
			
			ZzimListService zzimListService = new ZzimListServicelmpl();
			ArrayList<TagSearchDto> myList = zzimListService.execute(request, response);
			request.setAttribute("myList", myList);
			
			RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/myPage.jsp");
			requestDispatcher.forward(request, response);
			
		}
	}
}
