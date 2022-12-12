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
                <a href="main.do">
                    <h1>CAMPICK</h1>
                </a>
            </div>
            <div id="inform">
                <ul>
                    <li><a href="login.jsp">로그인</a></li>
                </ul>
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
        <div id="headline">
            <h2>커뮤니티</h2>
        <button type="button" onclick="writeBorad()"> 글쓰기 </button>
    </div><br><br><br>
            <ul id="gallery">
            	<%
            	for(BoradDto dto : (ArrayList<BoradDto>)boradList){
            	%>
                <a href="borad.do?action=detail&borad_id=<%=dto.getBorad_id()%>">
                    <li>
                        <div class="photo"><img src=<%=dto.getBorad_img() %>></div>
                        <div class="title"><%=dto.getBorad_name()%></div>
                        <div class="writer"><%=dto.getName() %> </div>
                        <div class="date"><%=dto.getBorad_date() %> </div>
                    </li></a>
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