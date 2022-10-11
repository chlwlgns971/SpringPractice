<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<c:set value="${board }" var="board" />
		<c:choose>
			<c:when test="${not empty board }">
				<tr>
					<td>글번호</td>
					<td>${board.boNo }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${board.boTitle }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.boWriter }</td>
				</tr>
				<tr>
					<td>아이피</td>
					<td>${board.boIp }</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${board.boMail }</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>${board.boPass }</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>${board.boContent }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${board.boDate }</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${board.boHit }</td>
				</tr>
				<tr>
					<td>추천수</td>
					<td>${board.boRec }</td>
				</tr>
				<tr>
					<td>부모글</td>
					<td>${board.boParent }</td>
				</tr>
			</c:when>
		</c:choose>
	</table>
</body>
</html>