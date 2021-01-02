<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ attribute name="replyId" type="java.lang.Integer" %>
<%@ attribute name="contentNo" type="java.lang.Integer" %>

<div>	
	<c:forEach items="${subReplyList }" var="subReply" varStatus="status">
		<c:if test="${subReply.replyId==replyId}">
		<div>
			<form action="${pageContext.request.contextPath }/subReply/modify.do" method="post">
			<script>
				$(function() {
					$("#sub-btn${status.count }").click(function() {		
						$("#sub-body${status.count }").removeAttr("readonly");
						$(this).hide();
						$("#sub-submit${status.count }").removeAttr("hidden");
					});
				});
			</script>
			<input type="text" name="subReplybody" id="sub-body${status.count }" value="${subReply.body }" class="form-control" readonly/>
			<c:if test="${errors.noModify }">
				<small class="form-text text-muted">내용을 입력하세요.</small>			
			</c:if>
			<c:if test="${errors.noPermission_sub }">
				<small class="form-text text-muted">삭제 권한이 없습니다.</small>			
			</c:if>
			<input type="text" name="contentNo" value="${contentNo }" hidden/>
			<input type="text" name="replyId" value="${replyId }" hidden/>  
			<input type="text" hidden name="subreplyNo" value="${subReply.num }" />          
			<span>${subReply.registerid }</span>
			 <c:if test="${authUser.id == subReply.registerid}">
		        <div class="button-container mt-3">
		          <input type="submit" value="수정 완료" hidden id="sub-submit${status.count }" class="btn btn-primary"/>
		          <button type="button" id="sub-btn${status.count }" class="btn btn-primary"><i class="fas fa-edit"></i> 수정</button>		          
        		  <a class="btn btn-danger" href="${pageContext.request.contextPath }/subReply/remove.do?subReplyNo=${subReply.num }&contentNo=${contentNo}"><i class="fas fa-trash-alt"></i> 삭제</a>
		        </div>		        
		      </c:if>
		      </form>	
		</div>
		</c:if>
	</c:forEach>
</div>