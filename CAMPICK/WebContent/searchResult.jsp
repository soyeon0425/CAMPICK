<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.user.model.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/searchResult.css">
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
<!--             <ul>
                <li><a href="login.jsp">로그인</a></li>
            </ul> -->
        </div>
    </header>

    <nav>
        <ul id="topMenu">
            <li><a href="search.jsp">캠핑장찾기</a></li>
            <li><a href="tagSearch.jsp">태그로 찾기</a></li>
            <li><a href="analysis.jsp">캠핑 예측Pick</a></li>
            <li><a href="boradList.do">커뮤니티</a></li>
        </ul>
    </nav>

    <div id="resultCount">
        <p>총  ${scDtoList.size()}개의 결과가 검색되었습니다.</p>
        <hr>
    </div>

    <div id="contents">
      <c:forEach var="scDto" items="${scDtoList }" varStatus="status">
      <div class="card">
        <img src="image/example.jpg" alt="캠핑장 사이트 사진">
        <div class="campinfo">
          <div class="campinfo_head">
            <a href="campDetail.jsp">${scDto.camp_name }</a>
              <div class="wishlist">
                <img src="image/wishlist.png" onclick="alert('찜 list에 추가되었습니다.')">
              </div>
            </div>
            <div class="campinfo_contents">
              <ul>
                <li>주소 : ${scDto.addr }</li>
                <c:if test="${scDto.tel != null }">
                	<li>전화번호 : ${ scDto.tel }</li>
                </c:if>
                <c:if test="${scDto.lineIntro != null }">
                	<li>${ scDto.lineIntro }</li>                
                </c:if>
              </ul>
            </div>
            <div class="campinfo_detail">
              <ul>
              	<c:forEach var="subplace" items="${subplaceList }" varStatus="status">
	                <li>${subplace}</li>
              	</c:forEach>
              </ul>
            </div>
        </div>
      </div>
      </c:forEach>
    </div>
    <footer>

    </footer>
</body>
</html>