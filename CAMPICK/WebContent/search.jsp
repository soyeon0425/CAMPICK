<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.user.model.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%  UserDto loginUser = (UserDto)session.getAttribute("loginUser");%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/detailsearch.css">
    <link rel="stylesheet" href="css/search.css">
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
                    <li><a href="login.html">로그인</a></li>
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
            <h2>내게 맞는 캠핑장 검색하기</h2>
            <fieldset>
               <form id="campSearch" method="post" name="form1" action="campList.do">
                    <h2>어디로 떠날까요?</h2>
                    <p>
                        <label>캠핑장 이름</label>
                        <input id="campName" name="camp_name" type="search" autofocus>
                    </p>
                    <p>
                        <label>캠핑 지역</label>
<!--                         <select id="location_cdo" name="location_cdo"> -->
<!--                             <option value="">-- 시/도 --</option> -->
<!--                             <option value="1">서울시</option> -->
<!--                             <option value="2">부산시</option> -->
<!--                             <option value="3">대구시</option> -->
<!--                             <option value="4">인천시</option> -->
<!--                             <option value="5">광주시</option> -->
<!--                             <option value="6">대전시</option> -->
<!--                             <option value="7">울산시</option> -->
<!--                             <option value="8">세종시</option> -->
<!--                             <option value="9">경기도</option> -->
<!--                             <option value="10">강원도</option> -->
<!--                             <option value="11">충청북도</option> -->
<!--                             <option value="12">충청남도</option> -->
<!--                             <option value="13">전라북도</option> -->
<!--                             <option value="14">전라남도</option> -->
<!--                             <option value="15">경상북도</option> -->
<!--                             <option value="16">경상남도</option> -->
<!--                             <option value="17">제주도</option> -->
<!--                         </select> -->
							<input list="location_cdo" id="input_cdo" name="donm" placeholder="-- 시/도 -- "/>
                       		 <datalist id="location_cdo">
                           		  <option value="경기도">
                      		  </datalist>
<!--                         <select id="location_gungu" name="location_gungu"> -->
<!--                             <option value="">-- 군/구 --</option> -->
<!--                             <option value="1">가평군</option> -->
<!-- 							<option value="2">고양시</option> -->
<!-- 							<option value="3">과천시</option> -->
<!-- 							<option value="4">광명시</option> -->
<!-- 							<option value="5">광주시</option> -->
<!-- 							<option value="6">구리시</option> -->
<!-- 							<option value="7">군포시</option> -->
<!-- 							<option value="8">김포시</option> -->
<!-- 							<option value="9">남양주시</option> -->
<!-- 							<option value="10">동두천시</option> -->
<!-- 							<option value="11">부천시</option> -->
<!-- 							<option value="12">성남시</option> -->
<!-- 							<option value="13">수원시</option> -->
<!-- 							<option value="14">시흥시</option> -->
<!-- 							<option value="15">안산시</option> -->
<!-- 							<option value="16">안성시</option> -->
<!-- 							<option value="17">안양시</option> -->
<!-- 							<option value="18">양주시</option> -->
<!-- 							<option value="19">양평군</option> -->
<!-- 							<option value="20">여주시</option> -->
<!-- 							<option value="21">연천군</option> -->
<!-- 							<option value="22">오산시</option> -->
<!-- 							<option value="23">용인시</option> -->
<!-- 							<option value="24">의왕시</option> -->
<!-- 							<option value="25">의정부시</option> -->
<!-- 							<option value="26">이천시</option> -->
<!-- 							<option value="27">파주시</option> -->
<!-- 							<option value="28">평택시</option> -->
<!-- 							<option value="29">포천시</option> -->
<!-- 							<option value="30">하남시</option> -->
<!-- 							<option value="31">화성시</option> -->
<!--                         </select> -->
						<input list="location_gungu" id="input_gungu" name="sigungu" placeholder="-- 군/구 -- "/>
                        <datalist id="location_gungu">
                           	  <option value="가평군">
                           	  <option value="고양시">
                           	  <option value="과천시">
                           	  <option value="광명시">
                           	  <option value="광주시">
                           	  <option value="구리시">
                           	  <option value="군포시">
                           	  <option value="김포시">
                           	  <option value="남양주시">
                           	  <option value="동두천시">
                           	  <option value="부천시">
                           	  <option value="성남시">
                           	  <option value="수원시">
                           	  <option value="시흥시">
                           	  <option value="안산시">
                           	  <option value="안성시">
                           	  <option value="안양시">
                           	  <option value="양주시">
                           	  <option value="양평군">
                           	  <option value="여주시">
                           	  <option value="연천군">
                           	  <option value="오산시">
                           	  <option value="용인시">
                           	  <option value="의왕시">
                           	  <option value="의정부시">
                           	  <option value="이천시">
                           	  <option value="파주시">
                           	  <option value="평택시">
                           	  <option value="포천시">
                           	  <option value="하남시">
                           	  <option value="화성시">
                        </datalist>
                    </p>
                    <p>
                        <label>캠핑 구분</label>
                        <input type="checkbox" id="site_01" name="camptype" value="일반야영장">일반야영장
                        <input type="checkbox" id="site_02" name="camptype" value="자동차야영장">자동차야영장
                        <input type="checkbox" id="site_03" name="camptype" value="카라반">카라반
                        <input type="checkbox" id="site_04" name="camptype" value="글램핑">글램핑
                    </p>
                    <button type="button" onclick="submit()"> 검색하기 </button> 
                </form>
                <button id="detailbutton" onclick="showPopup(true)">+</button>
            </fieldset>
        <div id="popup" class="hide">
            <div class="content">
                <form action="searchResult.jsp" method="post" id="popupform">
                    <h2>원하는 정보를 상세하게 입력해 보세요!</h2>
                    <ul>
                        <h3>입지구분</h3>
                        <li><input type="checkbox" value="beach">해변</li>
                        <li><input type="checkbox" value="island">섬</li>
                        <li><input type="checkbox" value="mountain">산</li>
                        <li><input type="checkbox" value="forest">숲</li>
                        <li><input type="checkbox" value="Valley">계곡</li>
                        <li><input type="checkbox" value="river">강</li>
                        <li><input type="checkbox" value="lake">호수</li>
                        <li><input type="checkbox" value="downtown">도심</li>
                    </ul>
                    <ul>
                        <h3>주요 시설</h3>
                        <li><input type="checkbox" value="camping">캠핑</li>
                        <li><input type="checkbox" value="chabak">차박</li>
                        <li><input type="checkbox" value="caravan">카라반</li>
                        <li><input type="checkbox" value="glamping">글램핑</li>
                    </ul>
                    <ul>
                        <h3>바닥 형태</h3>
                        <li><input type="checkbox" value="grass">잔디</li>
                        <li><input type="checkbox" value="crushed">파쇄석</li>
                        <li><input type="checkbox" value="deck">데크</li>
                        <li><input type="checkbox" value="Pebble">자갈</li>
                        <li><input type="checkbox" value="soil">맨흙</li>
                    </ul>
                    <ul>
                        <h3>테마별</h3>
                        <li><input type="checkbox" value="sunrise">일출명소</li>
                        <li><input type="checkbox" value="sunset">일몰명소</li>
                        <li><input type="checkbox" value="w_leisure">수상레저</li>
                        <li><input type="checkbox" value="a_leisure">항공레저</li>
                        <li><input type="checkbox" value="ski">스키</li>
                        <li><input type="checkbox" value="fishing">낚시</li>
                        <li><input type="checkbox" value="activity">액티비티</li>
                        <li><input type="checkbox" value="spring">봄꽃여행</li>
                        <li><input type="checkbox" value="summer">여름물놀이</li>
                        <li><input type="checkbox" value="autumn">가을단풍명소</li>
                        <li><input type="checkbox" value="winter">겨울눈꽃명소</li>
                        <li><input type="checkbox" value="walk">산책</li>
                    </ul>
                    <ul>
                        <h3>부대시설</h3>
                        <li><input type="checkbox" value="electricity">전기</li>
                        <li><input type="checkbox" value="wifi">Wi-Fi</li>
                        <li><input type="checkbox" value="firewood">장작판매</li>
                        <li><input type="checkbox" value="hotwater">온수</li>
                        <li><input type="checkbox" value="trampoline">트렘폴린</li>
                        <li><input type="checkbox" value="swimmingpool">물놀이장</li>
                        <li><input type="checkbox" value="n_playground">놀이터</li>
                        <li><input type="checkbox" value="trail">산책로</li>
                        <li><input type="checkbox" value="u_playground">운동장</li>
                        <li><input type="checkbox" value="exercise">운동시설</li>
                        <li><input type="checkbox" value="mart">마트,편의점</li>
                    </ul>
                <!-- <table>
                    <ul>
                        <li>기타정보</li>
                        <li><input type="checkbox" value="place">일반야영장</li>
                        <li><input type="checkbox" value="place">일반야영장</li>
                        <li><input type="checkbox" value="place">일반야영장</li>
                        <li><input type="checkbox" value="place">일반야영장</li>
                        <li><input type="checkbox" value="place">일반야영장</li>
                    </ul>
                </table> -->
                    <ul>
                        <h3>가격대</h3>
                        <li>
                            <input type="text" id="firstprice" value="0">~
                            <input type="text" id="secondprice" value="100000"> 원
                        </li>
                    </ul>
                    <div id="buttons">
                        <input type="submit" value="검색하기">
                        <input type="reset" value="다시체크">
                    </div>
                </form>
                <button id="closebutton" onclick="closePopup()">닫기</button>
            </div>
        </div>
    </div>
    <footer>
        
    </footer>
    </div>
    <script>
        function showPopup(multipleFilter) {
	        const popup = document.querySelector('#popup');
  
                if (multipleFilter) {
  	                popup.classList.add('multiple-filter');
                } else {
  	                popup.classList.remove('multiple-filter');
                }
            popup.classList.remove('hide');
        }

        function closePopup() {
	        const popup = document.querySelector('#popup');
            popup.classList.add('hide');
        }
    </script>
</body>
</html>