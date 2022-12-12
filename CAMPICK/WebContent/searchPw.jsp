<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>CAMPICK</title>
    <link rel="stylesheet" href="css/searchid.css">
</head>
<body>
    <div id="container">
    <div id="logo">
        <h1> <a href="main.do">CAMPICK</a></h1>
    </div>
<div id="contents">>
    <form id="form" action="">
        <h2>비밀번호 찾기</h2>
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="s_id" required autofocus></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="tel" name="s_email" required></td>
            </tr>
            <tr>
                <td>핸드폰 번호</td>
                <td><input type="tel" name="s_tel" required></td>
            </tr>
        </table>
        <input type="submit" value="비밀번호 찾기">
    </form>
</div>
</div>
<footer>
    
</footer>
</body>
</html>