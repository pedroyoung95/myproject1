<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>

<div>
	<c:forEach items="${replyList }" var="reply" varStatus="status">
		<div>
			<form action="${pageContext.request.contextPath }/reply/modify.do" method="post">
			<script>
				$(function() {
					$("#btn${status.count }").click(function() {		
						$("#body${status.count }").removeAttr("readonly");
						$(this).hide();
						$("#submit${status.count }").removeAttr("hidden");
					});
				});
			</script>
			<input type="text" name="body" id="body${status.count }" value="${reply.body }" class="form-control" readonly/>
			<c:if test="${errors.noReply }">
				<small class="form-text text-muted">댓글을 입력하세요.</small>			
			</c:if>
			<c:if test="${errors.noPermission }">
				<small class="form-text text-muted">삭제 권한이 없습니다.</small>			
			</c:if>
			<input type="text" name="no" value="${param.no }" hidden/>  
			<input type="text" hidden name="replyId" value="${reply.id }" />          
			<span>${reply.registerid }</span>
			 <c:if test="${authUser.id == reply.registerid}">
		        <div class="button-container mt-3">
		          <input type="submit" value="수정 완료" hidden id="submit${status.count }" class="btn btn-primary"/>
		          <button type="button" id="btn${status.count }" class="btn btn-primary"><i class="fas fa-edit"></i> 수정</button>		          
        		  <a class="btn btn-danger" href="${pageContext.request.contextPath }/reply/remove.do?no=${reply.id }&contentNo=${contentData.content.number}"><i class="fas fa-trash-alt"></i> 삭제</a>
		        </div>
		        <div id="btn1"></div>
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