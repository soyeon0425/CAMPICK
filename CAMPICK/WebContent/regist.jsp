<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <script src="js/registerCheck.js"></script>
    <link rel="stylesheet" href="css/register.css">
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

    <form name="joinForm" >
        <div id="registerForm">
        <h1>회원 가입</h1>
        
        <fieldset>
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="user_id" required autofocus ></td>
                       <td><button onclick="idCheck()">중복체크</button></td>
                       <input type="hidden" name="idCheckResult" value="idNotOk"/>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="user_pw" required placeholder="비밀번호 8~12자리 특수기호 포함"></td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td><input type="password" name="user_pw2" required></td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td><input type="text" name="user_name" required></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="user_addr" required></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="user_email" required></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="tel" name="user_tel" required></td>
                </tr>
            </table>
        </fieldset>
        </div>
<!--         <div id="favoriteForm">
        <h1>취향 선택 (필수x)</h1>
        <fieldset>
            <table>
                <tr>
                    <td colspan="6"> <span><b>■선호하는 캠핑 트렌드?</b></span></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="trand" value="camping">캠핑</td>
                    <td><input type="checkbox" name="trand" value="caraban">카라반</td>
                    <td><input type="checkbox" name="trand" value="glamping">글램핑</td>
                    <td><input type="checkbox" name="trand" value="carbak">차박</td>
                </tr>
                <tr></tr>
                <tr>
                    <td colspan="6"><span><b>■선호하는 캠핑 취향?</b></span></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="trand" value="camping">해변</td>
                    <td><input type="checkbox" name="trand" value="camping">섬</td>
                    <td><input type="checkbox" name="trand" value="camping">산</td>
                    <td><input type="checkbox" name="trand" value="camping">숲</td>  
                    <td><input type="checkbox" name="trand" value="camping">계곡</td>
                    <td><input type="checkbox" name="trand" value="camping">강</td>
                </tr>
                <tr>   
                    <td><input type="checkbox" name="trand" value="camping">호수</td>
                    <td><input type="checkbox" name="trand" value="camping">도심</td>
                </tr>
                <tr>
                    <td colspan="6"><span><b>■선호하는 바닥형태?</b></span></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="trand" value="camping">잔디</td>
                    <td><input type="checkbox" name="trand" value="camping">데크</td>
                    <td><input type="checkbox" name="trand" value="camping">파쇄석</td>
                    <td><input type="checkbox" name="trand" value="camping">자갈</td>
                    <td><input type="checkbox" name="trand" value="camping">맨흙</td>
                </tr>
                <tr>
                    <td colspan="6"><span><b>■선호하는 테마?</b></span></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="trand" value="camping">일출명소</td>
                    <td><input type="checkbox" name="trand" value="camping">일몰명소</td>
                    <td><input type="checkbox" name="trand" value="camping">수상레저</td>
                    <td><input type="checkbox" name="trand" value="camping">항공레저</td>
                    <td><input type="checkbox" name="trand" value="camping">스키</td>
                    <td><input type="checkbox" name="trand" value="camping">낚시</td>
                </tr>
                <tr>  
                    <td><input type="checkbox" name="trand" value="camping">액티비티</td> 
                    <td><input type="checkbox" name="trand" value="camping">봄꽃여행</td>
                    <td><input type="checkbox" name="trand" value="camping">물놀이</td>
                    <td><input type="checkbox" name="trand" value="camping">가을단풍명소</td>
                    <td><input type="checkbox" name="trand" value="camping">겨울눈꽃명소</td>
                    <td><input type="checkbox" name="trand" value="camping">걷기길</td> 
                </tr>
            </table>
    
        </fieldset>
      </div>
       -->
  
        <button type="submit" onclick="register()">가입하기</button>
        <button type="reset" value="다시 작성">다시작성</button>
    </form>
    </div>

    <footer>

    </footer>

</body>
</html>