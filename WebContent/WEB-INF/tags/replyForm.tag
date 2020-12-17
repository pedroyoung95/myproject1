<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer" %>
<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${pageContext.request.contextPath }/reply/add.do" method="post">
			<input type="text" value="${articleNo }" name="no" hidden />
			<input type="text" name="body" />
			<input type="submit" value="댓글등록" />
		</form>
	</div>
</c:if>