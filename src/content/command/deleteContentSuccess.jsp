<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>
  <u:navbar2 />

  <div class="container">
  	<div class="jumbotron">		  
		<h1 class="display-4">게시물이 삭제 되었습니다.</h1>
		<p class="lead">더이상 해당 게시물을 볼 수 없습니다.</p>
		<hr class="my-4">
		<p>컨텐츠를 즐기러 가볼까요?</p>
		<a class="btn btn-primary" href="${pageContext.request.contextPath }/content/list.do">게시글목록보기</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath }/index.jsp" role="button">홈으로</a>
	</div>
  </div>
</body>
</html>
