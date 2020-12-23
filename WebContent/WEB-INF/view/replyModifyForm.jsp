<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>댓글 수정</title>
</head>
<body>
<u:navbar2></u:navbar2>

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
          <h1>댓글 수정</h1>
          <form action="${pageContext.request.contextPath }/reply/modify.do" method="post">
            <input type="text" name="no" value="${param.no }" hidden/>
            <input type="text" name="contentNo" value="${param.contentNo }" hidden/>          
            <div class="form-group">
              <label for="input1-newReply">새 댓글</label>
              <input id="input1-newReply" name="body" type="text" class="form-control" value="${reply.body }"/>   
            </div>
            <button type="submit" class="btn btn-primary">댓글 수정</button>
          </form>        
        </div>
	</div>
</div>
</body>
</html>