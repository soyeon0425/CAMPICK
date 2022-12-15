<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.user.model.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/tagResult.css">

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

    <div id="contents">
            <ul>
                <li><input type="checkbox" id="반려견동반"><label for="반려견동반" class="labelAfterCheckBox">#반려견동반</label></li>
                <li><input type="checkbox" id="아이들 놀기 좋은"><label for="아이들 놀기 좋은" class="labelAfterCheckBox">#아이들 놀기 좋은</label></li>
                <li><input type="checkbox" id="물맑은"><label for="물맑은" class="labelAfterCheckBox">#물맑은</label></li>
            </ul>

            <h2 id="result">
                테마를 선택한 당신에게 추천하는 캠핑장
            </h2>


            <div class="card">
                <img src="image/example.jpg" alt="캠핑장 사이트 사진">
                <div class="campinfo">
                    <div class="campinfo_head">
                        <a href="campDetail.jsp">중앙 정보 캠핑장</a>
                        <div class="wishlist">
                            <img src="image/wishlist.png" onclick="alert('찜 list에 추가되었습니다.')">
                        </div>
                    </div>
                    <div class="campinfo_contents">
                        <ul>
                            <li>서울시 마포구 신촌로 176 5층</li>
                            <li>010-3333-4444</li>
                        </ul>
                    </div>
                    <div class="campinfo_detail">
                        <ul>
                            <li>전기</li>
                            <li>와이파이</li>
                            <li>장작판매</li>
                            <li>온수로</li>
                            <li>놀이터</li>
                        </ul>
                    </div>
                </div>
            </div>
                <div class="card">
                    <img src="image/example2.jpg" alt="캠핑장 사이트 사진">
                    <div class="campinfo">
                        <div class="campinfo_head">
                            <a href="campDetail.jsp">대한민국 캠핑장</a>
                            <div class="wishlist">
                                <img src="image/wishlist.png" onclick="alert('찜 list에 추가되었습니다.')">
                            </div>
                        </div>
                        <div class="campinfo_contents">
                            <ul>
                                <li>서울특별시 종로구 한길 29-12</li>
                                <li>02-1234-5678</li>
                            </ul>
                        </div>
                        <div class="campinfo_detail">
                            <ul>
                                <li>전기</li>
                                <li>와이파이</li>
                                <li>장작판매</li>
                                <li>온수로</li>
                                <li>놀이터</li>
                                <li>산책로</li>
                                <li>마트,편의점</li>
                            </ul>
                        </div>
    
                        </div>
                    </div>
    </div>

    <footer>

    </footer>
    </div>
    
</body>
</html>