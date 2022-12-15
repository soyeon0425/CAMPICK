<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.user.model.*,java.util.*, com.borad.model.*" %>
   	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");
    	BoradDao bDao = BoradDao.getInstance();
    	int dbCount = bDao.getDBcount();
    	
    	int pageSize = 9;
    	String pageNo = request.getParameter("page");
    	if(pageNo == null){
    		pageNo = "1";
    	}
    	int curPage = Integer.parseInt(pageNo);
    	int startRow = ((curPage-1) * pageSize) +1 ;
    	
    %>
<jsp:useBean id="boradList" scope="request" class="java.util.ArrayList"></jsp:useBean>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/comunity.css">
</head>
<body>
    <div id="container">
        <header>
            <div id="logo">
                <a href="main.do">
                    <h1>CAMPICK</h1>
                </a>
            </div>
            <div id="inform">
             <c:choose>
           	 <c:when test="${loginUser==null}">
           	 <ul>
              	<li><a href="login.jsp">로그인</a></li>
           	 </ul>
            </c:when>
            <c:otherwise>
             <ul>
            	<li><a href="userLogout.do">로그아웃</a></li>
         	    <li><a href="myPage.jsp">마이페이지</a></li>
           		<li style="color:white;"><%=loginUser.getName() %>님</li>
           	 </ul>
            </c:otherwise>
            </c:choose>
<!--                 <ul>
                    <li><a href="login.jsp">로그인</a></li>
                </ul> -->
            </div>
        <nav>
            <ul id="topMenu">
                <li><a href="search.jsp">캠핑장찾기</a></li>
                <li><a href="tagSearch.jsp">태그로 찾기</a></li>
                <li><a href="analysis.jsp">캠핑 예측Pick</a></li>
                <li><a href="boradList.do">커뮤니티</a></li>
                </ul>
        </nav>
    </header>
        <div id="headline">
            <h2>커뮤니티</h2>
             <c:choose>
           	 <c:when test="${loginUser==null}">
           	 </c:when>	
           	 <c:otherwise>
        		<button type="button" onclick="writeBorad()"> 글쓰기 </button>
           	 </c:otherwise>
           	 </c:choose>
    </div><br><br><br>
            <ul id="gallery">
            	<%
            	for(BoradDto dto : (ArrayList<BoradDto>)boradList){
            	%>
                <a href="boradDetail.do?borad_id=<%=dto.getBorad_id()%>">
                    <li>
                        <div class="photo">
                        	<c:set var="borad_img" value="<%=dto.getBorad_img() %>"></c:set>
                        	<c:choose>
	                        	<c:when test="${borad_img == null}">
	                        		<img src="image/noimage.png">
	                        	</c:when>	
	                        	<c:otherwise>
		                        	<img src="image/<%=dto.getBorad_img() %>">
	                        	</c:otherwise>
                        	</c:choose>
	                   	</div>
                        <div class="title"><%=dto.getBorad_name()%></div>
                        <div class="writer"><%=dto.getName() %> </div>
                        <div class="date"><%=dto.getBorad_date() %> </div>
                    </li>
               </a>
               <%
            	}
               %>
        </div>

    <footer>

    </footer>
    <script>
    function writeBorad(){
        document.location.href="/CAMPICK/writePage.jsp";
    }
</script>
</body>
</html>