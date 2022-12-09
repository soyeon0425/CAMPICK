package com.campick.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campick.service.BoradListService;
import com.campick.service.BoradListServiceImpl;

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
			System.out.println("由ъ뒪�듃�엯�땲�떎!");
			BoradListService listservice = new BoradListServiceImpl();
			listservice.execute(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("comunity.jsp");
			rd.forward(request, response);
		}
	}

}
