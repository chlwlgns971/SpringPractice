<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath }/resources/js/ckeditor/ckeditor.js">

</script>

<!-- modelAttribute과 commandName은 같은 의미 -->
<form:form method="post" modelAttribute="board" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="board.boTitle" /></th>
			<td>
				<form:input path="boTitle" required="true"/>
				<form:errors path="boTitle" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boWriter" /></th>
			<td>
				<form:input path="boWriter"/>
				<form:errors path="boWriter" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boIp"/></th>
			<td>
				<form:input path="boIp"/>
				<form:errors path="boIp" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="boFiles" />
				<input type="file" name="boFiles" />
				<input type="file" name="boFiles" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boMail"/></th>
			<td>
				<form:input path="boMail"/>
				<form:errors path="boMail" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boPass"/></th>
			<td>
				<form:input path="boPass" type="Password"/>
				<form:errors path="boPass" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boContent"/></th>
			<td>
				<form:textarea path="boContent" cols="50" rows="10"/>
				<form:errors path="boContent" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<form:button type="submit" class="btn btn-success">전송</form:button>
			</td>
		</tr>
	</table>
</form:form>
<script>
	CKEDITOR.replace('boContent', {
		 filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=image"
	});
</script>