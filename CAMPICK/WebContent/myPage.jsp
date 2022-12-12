<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/mypage.css">
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
                    <li><a href="login.html">로그인</a></li>
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
    </div>
    <div id="content">
        <div id="sidebar">
            <ul>
                <li>
                    <input type="radio" id="select" name="side_menu" checked><label for="select">내가 쓴 게시글</label>
                    <div id="notice"></div>
                </li>
                <li>
                    <input type="radio" id="select2" name="side_menu"><label for="select2">캠핑장 찜 리스트</label>
                </li>
            </ul>
            <form action="search.html">
                <button onclick="exit()">탈퇴하기</button>
            </form>
        </div>
        <div id="main">
            <div id="notice" class="tabContent">
                <h2><span>■</span>내가 쓴 글</h2>
                <ul>
                    <li>11월 가을 캠핑 후기!!! <span id="date">2202/11/28</span> <button onclick="remove()" id="n_remove">삭제</button><button onclick="edit()" id="n_edit">수정</button></li>
                </ul>
                <ul>
                    <li>이 사이트 좀 괜찮은듯? <span id="date">2202/11/28</span> <button onclick="remove()" id="n_remove">삭제</button><button onclick="edit()" id="n_edit">수정</button></li>
                </ul>
            </div>
            <div id="pick" class="tabContent">
                <h2><span>■</span>캠핑장 찜List</h2>
                <ul>
                    <li>
                        <h3>중앙 정보 캠핑장</h3> <button onclick="jjimRemove()">찜삭제</button>
                    </li>
                    <li>
                        <table>
                            <tr>
                                <td bgcolor="#375959">주소</td>
                                <td>서울시 서대문구</td>
                                <td bgcolor="#375959">번호</td>
                                <td>010-1234-1234</td>
                            </tr>
                        </table>
                    </li>
                </ul>
            </div>
            
        </div>
    </div>
    <div id="footer">
        <p>
            <!-- footer 입력 하는 곳 -->
        </p>
    </div>
    <script>
        function exit(){
            alert('탈퇴 하였습니다..!');
        }

        function edit(){
            alert('수정화면 이동 예정');
        }

        function remove(){
            alert('삭제될 예정');
        }
        function jjimRemove(){
            alert('삭제 될 예정');
        }
    </script>

    <footer>

    </footer>
</body>
</html>