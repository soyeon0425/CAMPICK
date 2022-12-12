<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.campick.model.*"%>
<%
	BoradDao dao = BoradDao.getInstance();
	BoradDto dto = dao.getDB((int)session.getAttribute("boradid"));
%>
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
            <form action="borad.do?action=edit" name="form1" method="post">
                <img src="<%=dto.getBorad_img()%>" alt="이미지" width="500px">
                <p>
                	<%=dto.getBorad_text() %>
                </p>
                <button id="w_edit">수정</button>
            </form>
            <button onclick="goList()" id="goList">목록</button>
            <button onclick="w_remove()" id="w_remove">삭제</button>
            <button onclick="w_good()" id="w_good">추천</button>
            <p id="dat">댓글 1개</p>
        </div>
        <hr style="border: solid 2px #eee;" width="90%">
        <div id="datgle">
            <form action="#">
                <input type="text" id="regit_datgle" placeholder="댓글을 입력하세요">
                <button>등록</button>
            </form>
            <ul>
                <li>
                    <table>
                        <tr>
                            <td>닉네임</td>
                            <td id="datcon">아뇨 별로 안이쁜데요?</td>
                            <td>22-11-30</td>
                            <td>17:42</td>
                        </tr>
                    </table>
                </li>
            </ul>
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
                document.location.href="borad.do?action=delete"
            }
        }
    </script>
</body>
</html>