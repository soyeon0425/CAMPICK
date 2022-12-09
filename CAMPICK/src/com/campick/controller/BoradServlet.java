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
//		String uri = request.getRequestURI();
//		String conPath = request.getContextPath();
//		String command = uri.substring(conPath.length());
		String action = request.getParameter("action");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
//		if(command.equals("/borad.do")) {
//			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
//			rd.forward(request, response);
//		}
		if(action.equals("list")) {
			System.out.println("list 진입");
			BoradListService boradListService = new BoradListServiceImpl();
			ArrayList<BoradDto> boradList= boradListService.execute(request, response);
			request.setAttribute("boradList", boradList);
			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
			rd.forward(request, response);
		}else if(action.equals("write")) {
			System.out.println("insert 진입");
			response.sendRedirect("writePage.jsp");
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
