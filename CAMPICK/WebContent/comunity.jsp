<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.campick.model.*,java.util.*" %>
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
                <a href="search.html">
                    <h1>CAMPICK</h1>
                </a>
            </div>
            <div id="inform">
                <ul>
                    <li><a href="login.html">로그인</a></li>
                </ul>
            </div>
        	<nav>
            	<ul id="topMenu">
                	<li><a href="search.html">캠핑장찾기</a></li>
                	<li><a href="tagSearch.html">태그로 찾기</a></li>
                	<li><a href="analysis.html">캠핑 예측Pick</a></li>
                	<li><a href="comunity.html">커뮤니티</a></li>
                	<li><a href="mypage.html">마이페이지</a></li>            
            	</ul>
        	</nav>
    	</header>
        <div id="headline">
            <h2>커뮤니티</h2>
      		<button type="button" onclick="readWrite()"> 글쓰기 </button>
    	</div>
    	<br><br><br>
        <ul id="gallery">
        <%
        	for(BoradDto  dto : (ArrayList<BoradDto>)boradList){        
        %>
        		<a href="writedetail.jsp">
        			<li>
        		</a>
<!--             <a href="writedetail.html"><li><img src="image/1.jpg" alt="1"></li></a> -->
<!--             <a href=""><li><img src="image/2.jpg" alt="2"></li></a> -->
<!--             <a href=""><li><img src="image/3.jpg" alt="3"></li></a> -->
<!--             <a href=""><li><img src="image/4.jpg" alt="4"></li></a> -->
<!--             <a href=""><li><img src="image/5.jpg" alt="5"></li></a> -->
<!--             <a href=""><li><img src="image/6.jpg" alt="6"></li></a> -->
		<%
        	}
		%>
        </ul>
    </div>
    <footer>
    </footer>
    <script>
	    function readWrite(){
	        document.location.href="writedetail.jsp";
	    }
</script>
</body>
</html>