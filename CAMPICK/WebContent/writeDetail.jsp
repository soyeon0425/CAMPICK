<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.campick.model.* , com.comment.model.*, java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	BoradDao dao = BoradDao.getInstance();
	BoradDto dto = dao.getDB((int)session.getAttribute("boradid"));
	UserDto loginUser = (UserDto)session.getAttribute("loginUser");
%>
<jsp:useBean id="commentList" scope="request" class="java.util.ArrayList"></jsp:useBean>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/writedetail.css">
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
            				<li><a href="user.do?action=logout">로그아웃</a></li>
         	    			<li><a href="myPage.jsp">마이페이지</a></li>
           					<li style="color:white;"><%=loginUser.getName() %>님</li>
           		 		</ul>
            		</c:otherwise>
            	</c:choose>
            </div>
           <nav>
            <ul id="topMenu">
                <li><a href="search.html">캠핑장찾기</a></li>
                <li><a href="tagSearch.html">태그로 찾기</a></li>
                <li><a href="analysis.html">캠핑 예측Pick</a></li>
                <li><a href="borad.do?action=list">커뮤니티</a></li>
                </ul>
            </nav>
        </header>

    <div id="content">
        <div id="writeinfo">
            <table id="table1">
                <tr>
                    <td class="title">글번호</td>
                    <td><%=dto.getBorad_id() %></td>
                    <td class="title">조회수</td>
                    <td><%=dto.getBorad_visit() %></td>
                    <td class="title">추천수</td>
                    <td><%=dto.getBorad_suggestion() %></td>
                    <td class="title">글작성일</td>
                    <td><%=dto.getBorad_date() %></td>
                </tr>
            </table>
            <table id="table2">
                <tr>
                    <td class="title">캠핑장</td>
                    <td><%=dto.getCamp_name() %></td>
                    <td class="title">기간</td>
                    <td><%=dto.getBorad_period_first() %> ~ <%=dto.getBorad_period_second() %></td>

                </tr>
                <tr>
                    <td class="title">글제목</td>
                    <td colspan="3"><%=dto.getBorad_name() %></td>
                </tr>
            </table>
            <span id="writer">작성자 <%=dto.getName() %></span>
        </div>
        <hr style="border: solid 2px #eee;" width="90%">
        <div id="writecontents">
            <img src="image/<%=dto.getBorad_img()%>" alt="이미지" width="500px" height="350px">
            <p>
             	<%=dto.getBorad_text() %>
            </p>
            <c:choose>
            	<c:when test="${loginUser != null }">
            	<%if(loginUser.getName().equals(dto.getName())){ %>
		            <button onclick="w_edit()" id="w_edit">수정</button>
		            <button onclick="w_remove()" id="w_remove">삭제</button>
	            	<button onclick="goList()" id="goList">목록</button>
    	        	<button onclick="w_good()" id="w_good">추천</button>
	            <%}else{ %>
    	        	<button onclick="w_good()" id="w_good">추천</button>
		           	<button onclick="goList()" id="goList">목록</button>
	            <%} %>
            	</c:when>
            </c:choose>
           	<button onclick="goList()" id="goList">목록</button>
        </div>
        <hr style="border: solid 2px #eee;" width="90%">
        <div id="datgle">
        	<% for(CommentDto cDto : (ArrayList<CommentDto>)commentList){%>
	        	<table>
	        		<tr>
	        			<td rowspan="2" align="center" width = "70px" style="border-right: 1px solid #eee "><%=cDto.getName() %></td>
	        			<td colspan="4" class="ganguk" style="border-bottom: 1px solid #eee"><%=cDto.getReply() %></td>
	        		</tr>
	        		<tr class="tablefont">
	        			<td height="10px" class="ganguk"><%=cDto.getReply_time() %></td>
	        			<%if(loginUser.getName().equals(dto.getName())){ %>
	        			<td align="center" width=35px>수정</td>
	        			<td align="center" width=35px>삭제</td>
	        			<td width=35px><button class="reComment" onclick="togleReComment()">답글</button></td>
	        			<%}else{ %>
	        			<td width=35px><button class="reComment" onclick="togleReComment()">답글</button></td>
	        			<%} %>
	        		</tr>
	        	</table>
	        	<div class="insertReComment">
       			 	<form action="borad.do?action=recomment" name=form3 method=post>
        				<c:choose>
            				<c:when test="${loginUser != null }">
	        					<table>
        							<tr>
        								<td width = 50px><img src="image/recomment.png" width="100%" height="100%"></td>
	        							<td width = "70px"><%=loginUser.getName() %></td>
        								<td><textarea rows="2" placeholder="댓글을 입력해주세요" name="reply" required></textarea></td>
        								<td width=30px><button>등록</button></td>
        						</table>
        					</c:when>
        				</c:choose>
        			</form>
        		</div>
	        <%} %>
        </div>
        <div id="insertComment">
        	<form action="borad.do?action=comment" name=form3 method=post>
        	<c:choose>
            	<c:when test="${loginUser != null }">
	        	<table>
        			<tr>
	        			<td width = "70px"><%=loginUser.getName() %></td>
        				<td><textarea rows="3" placeholder="댓글을 입력해주세요" name="reply" required></textarea></td>
        				<td width=30px><button>등록</button></td>
        		</table>
        		</c:when>
        		</c:choose>
        	</form>
        </div>
    </div>

</div>
    <script>
        function goList(){
            document.location.href="borad.do?action=list";
        }
        function w_remove(){
            var check = confirm("삭제 하시겠습니까?");
            if(check === true){
                alert('삭제 되었습니다.');
                document.location.href="borad.do?action=delete";
            }
        }
        function w_edit(){
        	document.location.href="borad.do?action=edit";
        }
        function togleReComment(){
       		document.querySelector('.insertReComment').style.display = 'block';
       		
//        		const comment = document.querySelectorAll(".insertReComment");
       		
        }
    </script>
</body>
</html>