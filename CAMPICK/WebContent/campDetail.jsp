<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/campDetail.css">
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
    </header>

    <nav>
        <ul id="topMenu">
            <li><a href="search.html">캠핑장찾기</a></li>
            <li><a href="tagSearch.html">태그로 찾기</a></li>
            <li><a href="analysis.html">캠핑 예측Pick</a></li>
            <li><a href="borad.do?action=list">커뮤니티</a></li>
            </ul>
    </nav>
    <div id="contents">
    <div class="headline">
    <h1>중앙 정보 캠핑장</h1>
    <h2>캠핑장 정보 자세히보기</h2>
    </div>
    <div class="tab">
        <div class="main" style="text-align:center">
 
            <input id="tab1" type="radio" name="tabs" checked> 
            <label for="tab1">시설정보</label>
         
            <input id="tab2" type="radio" name="tabs">
            <label for="tab2">위치정보</label>
            <input id="tab3" type="radio" name="tabs">
            <label for="tab3">날씨정보</label>
         
            <section id="content1" style="margin-bottom:20px;">
                <table>
                    <tr>
                        <th>주소</th>
                        <td>서울시 마포구 신촌로 176 5층</td>
                    </tr>
                    <tr>
                        <th>번호</th>
                        <td>010-3333-4444</td>
                    </tr>
                    <tr>
                        <th>캠핑장 환경</th>
                        <td>숲, 산</td>
                    </tr>
                    <tr>
                        <th>캠핑장 유형</th>
                        <td>일반야영장</td>
                    </tr>
                    <tr>
                        <th>홈페이지</th>
                        <td>홈페이지 바로가기</td>
                    </tr>
                </table>
            </section>
            <section id="content2" style="margin-bottom:20px;">
                <img src="image/map.PNG">
            </section>
            <section id="content3" style="margin-bottom:20px;">
                날씨정보
            </section>
    </div>
</div>
</div>

<footer>

</footer>
</body>
</html>