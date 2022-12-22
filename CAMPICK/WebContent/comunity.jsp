<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.user.model.*,java.util.*, com.borad.model.*" %>
   	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");
    	BoradDao bDao = BoradDao.getInstance();
    	int count = bDao.getDBcount(); //db에 잇는 게시글 수를 얻는다.
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
        <div id=page_contorl>
        	<ul>
        	<%
        		if(count != 0){
        			String reqPage = request.getParameter("page");
        	    	if(reqPage == null){
        	    		reqPage = "1";
        	    	}
        			int pageSize = 9; // 페이지당 보여주는 게시글 갯수
        			int pageCount = count / pageSize + (count % pageSize == 0? 0:1); //페이지 갯수
        	    	int curPage = Integer.parseInt(reqPage);
        	    	int pageBlock = 10;
        	    	
        	    	int startPage = ((curPage-1)/pageBlock)*pageBlock+1;
        	    	
        	    	int endPage = startPage + pageBlock-1;
        	    	if(endPage > pageCount){
        	    		endPage = pageCount;
        	    	}
        	    	if(curPage >1){
        	%>
        	    		<li><li class=page_li>
        	    			<a href="boradList.do?page=<%=curPage-1 %>">이전</a>
        	    		</li>
        	    		
        	<%		}
        			for(int i = startPage; i<=endPage; i++){ 
        				if(i == curPage){
        	%>
    					<li class=page_li><a href="boradList.do?page=<%=i%>"  id=curPage><%=i %></a></li>
    		<%			}else{ %>
        				<li class=page_li>
        					<a href=boradList.do?page=<%=i%>><%=i %></a>
        				</li>
        	<%			
        				}
        			}
        			if(endPage < pageCount){
        	%>
        				<li class=page_li>
        					<a href=boradList.do?page=<%=curPage +1 %>>다음</a>
        				</li>
        	<%
        			}
        		}
        	%>
        	</ul>
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