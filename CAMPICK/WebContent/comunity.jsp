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
                <li><a href="mypage.html">마이페이지</a></li>            </ul>
        </nav>
    </header>
        <div id="headline">
            <h2>커뮤니티</h2>
        <button type="button" onclick="location.href='writepage.html'"> 글쓰기 </button>
    </div><br><br><br>
            <ul id="gallery">
                <a href="writedetail.html">
                    <li>
                        <div class="photo"><img src="image/1.jpg"></div>
                        <div class="title">캠핑다녀왔어여어어어어어어어어어어어어</div>
                        <div class="writer">KIMSOSO </div>
                        <div class="date">2022-12-09 15:10 </div>
                    </li></a>
                <a href="">
                    <li>
                        <div class="photo"><img src="image/2.jpg"></div>
                        <div class="title">가을캠핑 굿</div>
                        <div class="writer">KIMSOSO </div>
                        <div class="date">2022-12-09 15:10 </div>
                    </li>
                </a>
                <a href="">
                    <li>
                    <div class="photo"><img src="image/3.jpg"></div>
                    <div class="title">아이들과 함께^^</div>
                    <div class="writer">민규맘 </div>
                    <div class="date">2022-12-09 15:10 </div>
                </li></a>
                <a href="">
                     <li>
                    <div class="photo"><img src="image/4.jpg"></div>
                    <div class="title">전골도 좋네요~</div>
                    <div class="writer">캠핑요리사 </div>
                    <div class="date">2022-12-09 15:10 </div>
                </li></a>
                <a href="">
                    <li>
                        <div class="photo"><img src="image/5.jpg"></div>
                        <div class="title">캠핑 준비끝났어요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!</div>
                        <div class="writer">고고고고고</div>
                        <div class="date">2022-12-09 15:10 </div>
                    </li>
                </a>
                <a href="">
                    <li>
                        <div class="photo"><img src="image/6.jpg"></div>
                        <div class="title">굿굿</div>
                        <div class="writer">happyhappy </div>
                        <div class="date">2022-12-09 15:10 </div>
                    </li>
                </a>
            </ul>
            
        </div>
    </div>

    <footer>

    </footer>
    <script>
    function readWrite(){
        document.location.href="writedetail.html";
    }
</script>
</body>
</html>