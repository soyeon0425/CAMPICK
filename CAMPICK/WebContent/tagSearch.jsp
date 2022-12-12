<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*, com.campick.model.*"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/tagSearch.css">

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
<!--                 <ul>
                    <li><a href="login.jsp">로그인</a></li>
                </ul> -->
            </div>


           <nav>
            <ul id="topMenu">
                <li><a href="search.jsp">캠핑장찾기</a></li>
                <li><a href="tagSearch.jsp">태그로 찾기</a></li>
                <li><a href="analysis.jsp">캠핑 예측Pick</a></li>
                <li><a href="borad.do?action=list">커뮤니티</a></li>
                </ul>
            </nav>
    </header>

    <div id="contents">

        <h2>어떤 캠핑장을 찾으시나요?<br><br>▼</h2>
        <form id="tag_search" action="tagResult.html" method="post">
            <ul>
                <li><input type="checkbox" id="반려견동반"><label for="반려견동반" class="labelAfterCheckBox">#반려견동반</label></li>
                <li><input type="checkbox" id="아이들 놀기 좋은"><label for="아이들 놀기 좋은" class="labelAfterCheckBox">#아이들 놀기 좋은</label></li>
                <li><input type="checkbox" id="물맑은"><label for="물맑은" class="labelAfterCheckBox">#물맑은</label></li>
                <li><input type="checkbox" id="별보기좋은"><label for="별보기좋은" class="labelAfterCheckBox">#별보기좋은</label></li>
                <li><input type="checkbox" id="가을"><label for="가을" class="labelAfterCheckBox">#가을</label></li>
                <li><input type="checkbox" id="둘레길"><label for="둘레길" class="labelAfterCheckBox">#둘레길</label></li>
                <li><input type="checkbox" id="캠핑카"><label for="캠핑카" class="labelAfterCheckBox">#캠핑카</label></li>
                <li><input type="checkbox" id="봄"><label for="봄" class="labelAfterCheckBox">#봄</label></li>
                <li><input type="checkbox" id="가족"><label for="가족" class="labelAfterCheckBox">#가족</label></li>
                <li><input type="checkbox" id="커플"><label for="커플" class="labelAfterCheckBox">#커플</label></li>
                <li><input type="checkbox" id="수영장 있는"><label for="수영장 있는" class="labelAfterCheckBox">#수영장 있는</label></li>
                <li><input type="checkbox" id="그늘이 많은"><label for="그늘이 많은" class="labelAfterCheckBox">#그늘이 많은</label></li>
                <li><input type="checkbox" id="여름"><label for="여름" class="labelAfterCheckBox">#여름</label></li>
                <li><input type="checkbox" id="힐링"><label for="힐링" class="labelAfterCheckBox">#힐링</label></li>
                <li><input type="checkbox" id="계곡옆"><label for="계곡옆" class="labelAfterCheckBox">#계곡옆</label></li>
                <li><input type="checkbox" id="물놀이 하기 좋은"><label for="물놀이 하기 좋은" class="labelAfterCheckBox">#물놀이 하기 좋은</label></li>
                <li><input type="checkbox" id="겨울"><label for="겨울" class="labelAfterCheckBox">#겨울</label></li>
                
                <li><input type="checkbox" id="차대기 편한"><label for="차대기 편한" class="labelAfterCheckBox">#차대기 편한</label></li>
                <li><input type="checkbox" id="바다가 보이는"><label for="바다가 보이는" class="labelAfterCheckBox">#바다가 보이는</label></li>
                <li><input type="checkbox" id="깨끗한"><label for="깨끗한" class="labelAfterCheckBox">#깨끗한</label></li>
            </ul>

      <button type="button" onclick = "location.href = 'tagResult.jsp'">검색하기</button>
        </form>


    </div>
    </div>
    <script>
        function searching(){
            document.location.href="tagResult.html";
        }
    </script>    
</body>
</html>