<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<div>
	<c:forEach items="${replyList }" var="reply">
		<div>
			<input type="text" value="${reply.body }" class="form-control" readonly/>
			<span>${reply.registerid }</span>
			 <c:if test="${authUser.id == reply.registerid}">
		        <div class="button-container mt-3">
		          <a class="btn btn-primary" href="${pageContext.request.contextPath }/reply/modify.do?no=${reply.id }"><i class="fas fa-edit"></i> 수정</a>
        		  <a class="btn btn-danger" href="${pageContext.request.contextPath }/reply/remove.do?no=${reply.id }"><i class="fas fa-trash-alt"></i> 삭제</a>
		        </div>
		      </c:if>
		</div>
	</c:forEach>
</div>