<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/writepage.css">
</head> 
<body>
    <div id="container">
    <header>
        <div id="logo">
            <a href="search.html">
                <h1>CAMPING?</h1>
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
            <li><a href="mypage.html">마이페이지</a></li>        </ul>
    </nav>
</header>

    <div id="contents">
    <div class="headline">
    <h1>커뮤니티 글쓰기</h1>
    <h2>나의 캠핑 후기 공유하기!</h2>
    </div>
    <div class="tab">
        <div class="main" style="text-align:center">

                            <form id="form2" method="post" action="comunity.html">
                                <ul>
                                    <li><dt>제목<span class="font05"> *</span></dt><dd><input type="text" style="width:100%;"></input></dd></li>
                                    
                                    <li>
                                        <dt>여행 지역</dt>
                                        <dd>
                                <select id="location_cdo" name="location_cdo">
                                    <option value="">-- 시/도 --</option>
                                    <option value="1">서울시</option>
                                    <option value="2">부산시</option>
                                    <option value="3">대구시</option>
                                    <option value="4">인천시</option>
                                    <option value="5">광주시</option>
                                    <option value="6">대전시</option>
                                    <option value="7">울산시</option>
                                    <option value="8">세종시</option>
                                    <option value="9">경기도</option>
                                    <option value="10">강원도</option>
                                    <option value="11">충청북도</option>
                                    <option value="12">충청남도</option>
                                    <option value="13">전라북도</option>
                                    <option value="14">전라남도</option>
                                    <option value="15">경상북도</option>
                                    <option value="16">경상남도</option>
                                    <option value="17">제주도</option>
                            </select>
                            <select id="location_gungu" name="location_gungu">
                            <option value="">-- 군/구 --</option>
                            </select>
                            </dd>
                        </li>
                        <li>
                            <dt>여행일자</dt>
                            <dd>
                                <input type="date" style="width:130px;" value="2018.01.12">&nbsp;~&nbsp;
                                <input type="date" style="width:130px;" value="2018.01.12">
                            </dd>
                        </li>
                        
                            <li class="textarea" style="height:180px;">
                                <dt>내용<span class="font05"> *</span></dt>
                                <dd>
                                    <p><textarea style="width:100%; height:180px"></textarea></p>
                                </dd>
                            </li>
                            <li>
                                <dt>첨부파일</dt>
                                <dd>
                                    <div class="filebox">
                                        <input class="upload-name" value="대표 사진 선택" readonly>
                                      
                                        <label for="filename">업로드</label> 
                                        <input type="file" id="filename" class="upload-hidden"> 
                                      </div>
                                    </dd>
                                </li>
                            </ul>
                        <button onclick="submitWriter()"> 제출하기 </button> 
                        </form>
                    </div>
                    </div>

                 </div>
    </div>

<footer>

</footer>
<script>

    function submitWriter(){
        alert("등록 되었습니다.");
        document.location.href="comunity.html";
    }
</script>
</body>
</html>

