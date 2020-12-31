<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	$("#modify-btn").click(function() {		
		$("#body-textarea").removeAttr("readonly");
		$(this).hide();
		$("#submit-btn").removeAttr("hidden");
	});
});
</script>
<div>
	<c:forEach items="${replyList }" var="reply">
		<div>
			<form action="${pageContext.request.contextPath }/reply/modify.do" method="post">
			<input type="text" name="body" id="body-textarea" value="${reply.body }" class="form-control" readonly/>
			<input type="text" name="no" value="${param.no }" hidden/>
            <input type="text" name="contentNo" value="${param.contentNo }" hidden/>
			<span>${reply.registerid }</span>
			 <c:if test="${authUser.id == reply.registerid}">
		        <div class="button-container mt-3">
		          <input type="submit" value="수정 완료" hidden id="submit-btn" class="btn btn-primary"/>
		          <a id="modify-btn" class="btn btn-primary"><i class="fas fa-edit"></i> 수정</a>
        		  <a class="btn btn-danger" href="${pageContext.request.contextPath }/reply/remove.do?no=${reply.id }&contentNo=${contentData.content.number}"><i class="fas fa-trash-alt"></i> 삭제</a>
		        </div>
		      </c:if>
		      </form>
		      <%-- <c:forEach items="${subreplyList }" var="subreply">
			      <div class="row">
				      <div class="col-2"></div>
				      <div class="col-10">
				      
				      </div>
			      </div>
		      </c:forEach>  --%>
		</div>
	</c:forEach>
</div>