<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<div>
	<c:forEach items="${replyList }" var="reply">
		<div>
			<input type="text" value="${reply.body }" readonly/>
			<span>${reply.memberid }</span>
		</div>
	</c:forEach>
</div>