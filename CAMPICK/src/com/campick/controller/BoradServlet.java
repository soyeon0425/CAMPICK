package com.campick.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campick.model.BoradDto;
import com.campick.service.BoradUpdateService;
import com.campick.service.BoradUpdateServiceImpl;
import com.campick.service.BoradDeleteService;
import com.campick.service.BoradDeleteServiceImpl;
import com.campick.service.BoradListService;
import com.campick.service.BoradListServiceImpl;
import com.campick.service.BoradWriteDetailService;
import com.campick.service.BoradWriteDtailServiceImpl;
import com.campick.service.BoradWriteService;
import com.campick.service.BoradWriteServiceImpl;
import java.io.File;
import java.util.Enumeration;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

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
		try {
			actionDo(request,response);
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
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
			
		//글쓰기 화면에서 db로 입력
		}else if(action.equals("insert")){
//			BoradDto dto = new BoradDto();
//			dto.setBorad_name(request.getParameter("borad_name"));
//			dto.setCamp_name(request.getParameter("camp_name"));
//			dto.setBorad_period_first(request.getParameter("borad_period_first"));
//			dto.setBorad_period_second(request.getParameter("borad_period_second"));
//			dto.setBorad_text(request.getParameter("borad_text"));
//			dto.setBorad_img(request.getParameter("borad_img"));
//			
//			request.setAttribute("dto", dto);
			
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

		//수정화면으로 이동
		}else if(action.equals("edit")) {
			System.out.println("edit 진입");
			RequestDispatcher rd = request.getRequestDispatcher("writeEdit.jsp");
			rd.forward(request, response);
			
		//수정 데이터 DB 변경	
		}else if(action.equals("update")) {
			System.out.println("update 진입");
			
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
			
			BoradUpdateService bus = new BoradUpdateServiceImpl();
			bus.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("writeDetail.jsp");
			rd.forward(request, response);
			
		//게시판 삭제
		}else if(action.equals("delete")) {
			System.out.println("delete 진입");
			
			BoradDeleteService bds = new BoradDeleteServiceImpl();
			bds.execute(request, response);
			response.sendRedirect("borad.do?action=list");
		}
	}

}
