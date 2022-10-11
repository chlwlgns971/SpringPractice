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
	<c:set var="boardList" value="${pagingVO.dataList }" />
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty boardList }">
					<c:forEach var="board" items="${boardList }">
						<c:url value="/board/boardView.do" var="viewURL">
							<c:param name="what" value="${board.boNo }" />
						</c:url>
						<tr>
							<th>${board.boNo }</th>
							<th><a href="${viewURL }">${board.boTitle }</a></th>
							<th>${board.boWriter }</th>
							<th>${board.boDate }</th>
							<th>${board.boHit }</th>
							<th>${board.boRec }</th>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<th colspan="6">데이터가 존재하지 않음.</th>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</body>
</html>