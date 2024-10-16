<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>학생 정보 수정</title>
</head>
<body>
    <h2>학생 정보 수정</h2>
    <form action="student" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${student.studentId}">
        이름: <input type="text" name="name" value="${student.name}" required><br>
        학과: <input type="text" name="department" value="${student.department }" required><br>
        이메일: <input type="email" name="email" value="${student.email }" required><br>
        전화번호: <input type="text" name="phoneNumber" value="${student.phoneNumber }" required><br>
        <input type="submit" value="수정">
    </form>
</body>
</html>