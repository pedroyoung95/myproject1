<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<div class="container mb-3">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">Pedro's</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/index.jsp"><i class="fas fa-home"></i>Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/content/list.do"><i class="far fa-list-alt"></i>글 목록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/content/write.do"><i class="fas fa-edit"></i>글 쓰기</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/gallery/list.do"><i class="fas fa-images"></i>갤러리</a>
        </li>
       </ul>
       
       <u:notLogin>
	       <ul class="navbar-nav">
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/join.do" class="nav-link"><i class="fas fa-user-circle"></i>회원가입</a>
	       	</li>
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/login.do" class="nav-link"><i class="fas fa-sign-in-alt"></i>로그인</a>
	       	</li>
	       </ul>
       </u:notLogin>
       
       <u:isLogin>
	       <ul class="navbar-nav">
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/readInfo.do" class="nav-link"><i class="fas fa-info"></i>회원 정보</a>
	       	</li>
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/changePwd.do" class="nav-link"><i class="fas fa-key"></i>암호 변경</a>
	       	</li>
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/removeRegister.do" class="nav-link"><i class="fas fa-users-slash"></i>회원 탈퇴</a>
	       	</li>
	       	<li class="nav-item">
	       		<a href="${pageContext.request.contextPath }/logout.do" class="nav-link"><i class="fas fa-sign-out-alt"></i>로그아웃</a>
	       	</li>
	       </ul>
       </u:isLogin>
       
    </div>
</nav>
</div>