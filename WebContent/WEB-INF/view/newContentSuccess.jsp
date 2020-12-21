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
<title>게시글 등록 성공</title>
</head>
<body>
<u:navbar2></u:navbar2>

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		
		<div class="col-6">
			<h1>게시글을 등록했습니다.</h1>
			<br />
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/content/list.do">게시글목록보기</a>
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/content/read.do?no=${newContentNo}">게시글내용보기</a>
		</div>
		
		<div class="col-3"></div>
	</div>	
</div>
</body>
</html>