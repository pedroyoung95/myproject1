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
<title>Insert title here</title>
</head>
<body>

<u:navbar2></u:navbar2>

<div class="container">

	<div class="row">
	
		<div class="col-3"></div>
		
		<div class="col-6"> 
		<h1>회원가입</h1>
		<form action="join.do" method="post">		
		  <div class="form-group">
		    <label for="input1-id">아이디</label>
		    <input type="text" class="form-control" name="id" id="input1-id" value="${param.id }">
		    <c:if test="${errors.id }">
		    	<small class="form-text text-danger">ID를 입력하세요.</small>
		    </c:if> 
		    <c:if test="${errors.duplicatedId }">
		    	<small class="form-text text-danger">이미 사용중인 아이디입니다.</small>
		    </c:if>	 
		  </div>
		  
		  <div class="form-group">
		    <label for="input2-name">이름</label>
		    <input type="text" class="form-control" name="name" id="input2-name" value="${param.name }">    
			<c:if test="${errors.name }">
				<small id="emailHelp" class="form-text text-danger">이름을 입력하세요.</small>
			</c:if>	
		  </div>
		  
		  <div class="form-group">
		    <label for="input3-pwd">암호</label>
		    <input type="password" class="form-control" name="password" id="input3-pwd">    
			<c:if test="${errors.password }">
				<small id="emailHelp" class="form-text text-danger">암호를 입력하세요</small>
			</c:if>	
		  </div>
		  
		  <div class="form-group">
		    <label for="input4-confirm">확인</label>
		    <input type="password" class="form-control" name="confirmPassword" id="input4-confirm">    
				<c:if test="${errors.confirmPassword }">
					<small id="emailHelp" class="form-text text-danger">확인을 입력하세요</small>
				</c:if>
				<c:if test="${errors.notMatch }">
					<small id="emailHelp" class="form-text text-danger">암호와 확인이 일치하지 않습니다.</small>
				</c:if>		
		  </div>
		  
		  <button type="submit" class="btn btn-primary">회원가입</button>
		</form>
		</div>
		
		<div class="col-3"></div>
		
	</div>	
</div>
</body>
</html>