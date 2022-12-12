<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.campick.model.*"%>
    
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>

<%
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
	
// 	pageContext.forward("CAMPICK/borad.do?action=insert");
%>
<jsp:forward page="/borad.do?action=insert"></jsp:forward>
