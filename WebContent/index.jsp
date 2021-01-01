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
<title>my web app</title>
</head>
<body>
<u:navbar2></u:navbar2>


<div class="container">
	<u:isLogin>
		<div class="jumbotron">		  
		  <h1 class="display-4">${authUser.name }님, 안녕하세요!</h1>
		  <p class="lead">재밌는 컨텐츠를 즐겨보세요!</p>
		  <hr class="my-4">
		  <p>회원가입이 필요하신가요?</p>
		  <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath }/join.do" role="button">회원가입</a>
		</div>
		<br />		
	  </u:isLogin>
	  
	  <u:notLogin>
	  	<div class="jumbotron">
		  <h1 class="display-4">어서오세요!</h1>	  
		  <p class="lead">새로운 커뮤니티로 들어오세요!</p>
		  <hr class="my-4">
		  <p>회원가입부터 시작하세요!</p>
		  <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath }/join.do" role="button">회원가입</a>
		</div>
	  </u:notLogin>	
</div>
</body>
</html>