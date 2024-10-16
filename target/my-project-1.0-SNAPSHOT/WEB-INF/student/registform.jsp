<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>학생 등록</title>
</head>
<body>
    <h2>학생 정보 등록</h2>
    <form action="student" method="POST">
    	<input type="hidden" name="action" value="regist">
        이름: <input type="text" name="name"><br>
        학과: <input type="text" name="department"><br>
        이메일: <input type="email" name="email"><br>
        전화번호: <input type="text" name="phoneNumber"><br>
        <input type="submit" value="등록">
    </form>
</body>
</html>