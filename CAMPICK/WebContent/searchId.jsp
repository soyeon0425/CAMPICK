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
     	   <h1> <a href="search.html">CAMPICK</a></h1>
    	</div>
		<div id="contents">>
    		<form id="form" action="">
       		 <h2>ID찾기</h2>
        		<table>
           			<tr>
                		<td>이름</td>
                		<td><input type="text" name="s_name" required autofocus></td>
            		</tr>
            		<tr>
                		<td>핸드폰 번호</td>
                		<td><input type="tel" name="s_tel" required></td>
            		</tr>
        		</table>
        	<input type="submit" value="아이디 찾기">
    		</form>
		</div>
	</div>
	<footer>
	    
	</footer>
</body>
</html>