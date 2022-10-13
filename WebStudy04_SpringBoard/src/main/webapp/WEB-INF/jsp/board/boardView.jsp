<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<tr>
					<td colspan="2">
						<c:url value="/board/boardUpdate.do" var="updateURL">
							<c:param name="what" value="${board.boNo }"/>
						</c:url>
						<a href="${updateURL }" class="btn-btn-primary">글 수정</a>
						<a class="btn-btn-danger" id="deleteBtn">글 삭제</a>
					</td>
				</tr>
			</c:when>
		</c:choose>
	</table>
	
<!-- 	TestDrivenDevelopment vs EventDrivenDevelopment -->
	<c:set var="cPath" value="${pageContext.request.contextPath }" scope="application" />
	<form name="deleteForm" method="post" action="${cPath }/board/boardDelete.do">
		<input type="hidden" name="boNo" value="${board.boNo }"/>
		<input type="hidden" name="boPass"/>
	</form>
	<script>
		$('#deleteBtn').on("click", function(){
			event.preventDefault();
			var password = prompt('게시글 비밀번호를 입력하세요.');
			if(password){
				document.deleteForm.boPass.value = password;
				document.deleteForm.submit();
				document.deleteForm.boPass.value="";
				document.deleteForm.reset();
			}
			return false;
		})	
	</script>