<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>학생 목록</title>
</head>
<body>
	<h2>학생 목록</h2>
	<table border="1">
		<tr>
			<th>학생 ID</th>
			<th>이름</th>
			<th>학과</th>
		</tr>
		<c:forEach items="${list }" var="student">
			<tr>
				<td>${student.studentId}</td>
				<td><a href="student?action=detail&id=${student.studentId}">${student.name}</a></td>
				<td>${student.department}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="student?action=registform">학생 등록</a>
</body>
</html>
