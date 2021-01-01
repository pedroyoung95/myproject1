<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="contentNo" type="java.lang.Integer" %>
<%@ attribute name="replyId" type="java.lang.Integer" %>
<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${pageContext.request.contextPath }/subReply/add.do" method="post">			
			<input type="text" value="${contentNo }" name="contentNo" hidden />
			<input type="text" name="subReplybody" class="form-control"/>
			<c:if test="${errors.noSubReply }">
				<small class="form-text text-muted">내용을 입력하세요.</small>			
			</c:if>
			<input type="submit" class="btn btn-success" value="댓글 등록"/>	
		</form>		
	</div>
</c:if>