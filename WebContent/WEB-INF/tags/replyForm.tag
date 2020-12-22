<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="contentNo" type="java.lang.Integer" %>
<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${pageContext.request.contextPath }/reply/add.do" method="post">
			<input type="text" value="${contentNo }" name="no" hidden />
			<input type="text" name="body" class="form-control"/>
			<input type="submit" class="btn btn-primary" value="댓글 등록"/>	
		</form>		
	</div>
</c:if>