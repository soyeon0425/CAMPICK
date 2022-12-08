<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <a href="search.html">
                <h1>CAMPICK</h1>
            </a>
        </div>
        <div id="inform">
            <ul>
                <li><a href="login.html">로그인</a></li>
            </ul>
        </div>
    </header>

    <nav>
        <ul id="topMenu">
            <li><a href="search.html">캠핑장찾기</a></li>
            <li><a href="tagSearch.html">태그로 찾기</a></li>
            <li><a href="analysis.html">캠핑 예측Pick</a></li>
            <li><a href="comunity.html">커뮤니티</a></li>
            <li><a href="mypage.html">마이페이지</a></li>        
        </ul>
    </nav>

    <div id="resultCount">
        <p>총 1234개의 결과가 검색되었습니다.</p>
        <hr>
    </div>

    <div id="contents">
        <div class="card">
            <img src="image/example.jpg" alt="캠핑장 사이트 사진">
            <div class="campinfo">
                <div class="campinfo_head">
                    <a href="campDetail.html">중앙 정보 캠핑장</a>
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
                        <a href="campDetail.html">대한민국 캠핑장</a>
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
    </div>
        <footer>

    </footer>
</body>
</html>