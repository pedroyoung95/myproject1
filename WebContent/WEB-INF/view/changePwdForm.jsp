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
<title>암호 변경</title>
</head>
<body>
<u:navbar2></u:navbar2>

<div class="container">
	<div class="row">
	<div class="col-3"></div>
	
	<div class="col-6">
		<h1>암호변경</h1>
		<form action="changePwd.do" method="post">
			<div class="form-group">
				<label for="input1-curPwd">현재 암호 </label>
				<input type="password" name="curPwd" id="input1-curPwd" class="form-control"/>
				<c:if test="${errors.curPwd }">
					<small class="form-text text-danger">현재 암호를 입력하세요.</small>
				</c:if>
				<c:if test="${errors.badCurPwd }">
					<small class="form-text text-danger">현재 암호가 일치하지 않습니다.</small>
				</c:if>
			</div>
			
			<div class="form-group">
				<label for="input2-newPwd">새 암호 </label>
				<input type="password" name="newPwd" id="input2-newPwd" class="form-control"/>
				<c:if test="${errors.newPwd }">
					<small class="form-text text-danger">새 암호를 입력하세요.</small>
				</c:if>
			</div>
			<input type="submit" class="btn btn-primary" value="암호 변경" />
			<a class="btn btn-secondary" href="${pageContext.request.contextPath }/findPwd.do" role="button">암호 찾기</a>
		</form>
	</div>
	
	<div class="col-3"></div>
	</div>
</div>
</body>
</html>