<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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
<title>회원 정보</title>
</head>
<body>
<u:navbar2></u:navbar2>
	
<div class="container">
	<div class="row">
	<div class="col-3"></div>
		
	<div class="col-6">
	<h1>회원 정보</h1>				
		<div class="form-group">
			   <label for="input1-id">아이디</label>
			   <input type="text" class="form-control" name="id" value="${authUser.id }" id="input1-id" readonly/>				
		</div>			
		<div class="form-group">
			<label for="input2-pwd">이름</label>
			<input type="text" class="form-control" name="name" value="${authUser.name }" id="input2-pwd" readonly/>				
		</div>	
		<div class="form-group">
			<label for="input3-regdate">가입일</label>
			<input type="text" class="form-control" name="regdate" value="${authUser.regdate }" id="input3-regdate" readonly/>				
		</div>
		<div class="form-group">
			<label for="input4-wroteCnt">작성 게시글 수</label>
			<input type="text" class="form-control" name="wroteCnt" value="${authUser.wroteCnt }" id="input4-wroteCnt" readonly/>				
		</div>
		<div class="form-group">
			<label for="input5-replyCnt">작성 댓글 수</label>
			<input type="text" class="form-control" name="replyCnt" value="${authUser.replyCnt }" id="input4-replyCnt" readonly/>				
		</div>
	</div>
				
	<div class="col-3"></div>
	</div>
</div>
</body>
</html>