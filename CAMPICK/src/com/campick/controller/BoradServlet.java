package com.campick.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDto;
import com.campick.service.BoradListService;
import com.campick.service.BoradListServiceImpl;
import com.campick.service.BoradWriteDetailService;
import com.campick.service.BoradWriteDtailServiceImpl;
import com.campick.service.BoradWriteService;
import com.campick.service.BoradWriteServiceImpl;

/**
 * Servlet implementation class BoradServlet
 */
@WebServlet("/borad.do")
public class BoradServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoradServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request,response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action"); //action 변수로 기능 판별
		//커뮤니티 글 목록으로 이동
		if(action.equals("list")) {
			System.out.println("list 진입");
			BoradListService boradListService = new BoradListServiceImpl();
			ArrayList<BoradDto> boradList= boradListService.execute(request, response);
			request.setAttribute("boradList", boradList);
			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
			rd.forward(request, response);
			
//		//글쓰기 화면으로 이동
//		}else if(action.equals("write")) {
//			System.out.println("write 진입");
//			response.sendRedirect("writePage.jsp");
//			
		//글쓰기 화면에서 db로 입력
		}else if(action.equals("insert")){
			BoradDto dto = new BoradDto();
			dto.setBorad_name(request.getParameter("borad_name"));
			dto.setCamp_name(request.getParameter("camp_name"));
			dto.setBorad_period_first(request.getParameter("borad_period_first"));
			dto.setBorad_period_second(request.getParameter("borad_period_second"));
			dto.setBorad_text(request.getParameter("borad_text"));
			dto.setBorad_img(request.getParameter("borad_img"));
			
			request.setAttribute("dto", dto);
			BoradWriteService bws = new BoradWriteServiceImpl();
			bws.execute(request, response);
			response.sendRedirect("borad.do?action=list");
			
		//커뮤니티 글 상세 화면으로 이동
		}else if(action.equals("detail")) {
			System.out.println("detail 진입");
			HttpSession session= request.getSession();
			session.setAttribute("boradid", Integer.parseInt(request.getParameter("borad_id")));
			BoradWriteDetailService bwds = new BoradWriteDtailServiceImpl();
			bwds.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("writeDetail.jsp");
			rd.forward(request, response);
		}
	}

}
