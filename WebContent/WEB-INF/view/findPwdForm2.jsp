<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>암호 찾기</title>
</head>
<body>
<u:navbar2></u:navbar2>

<div class="container">
	<div class="row">
	<div class="col-3"></div>
	
	<div class="col-6">
		<h1>새 암호 생성</h1>
		<form action="" method="post">			
			<div class="form-group">
			    <label for="input1-id">새 암호</label>
			    <input type="password" class="form-control" name="password" id="input1-id"/>
				<c:if test="${errors.password }">
					<small class="form-text text-danger">암호를 입력하세요</small>
				</c:if>
			</div>			
		</form>
	</div>
	
	<div class="col-3"></div>
	</div>
</div>
</body>
</html>