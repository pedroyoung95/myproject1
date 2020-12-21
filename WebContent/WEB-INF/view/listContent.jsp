<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>게시글 목록</title>
<style>
.num-col {
  width: 10%;
}

.title-col {
  width: 60%;
}

.writer-col {
  width: 20%;
}

.read-col {
  width: 10%;
}
</style>
</head>
<body>
  <u:navbar2></u:navbar2>

  <div class="container">
    <div class="row justify-content-center">
    	<div class="col-6">
          <h1>게시글 목록</h1>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-8">

        <div class="list-container">
          <table class="table table-striped">
            <thead>
              <tr>
                <th class="num-col text-right"><i class="fas fa-hashtag"></i></th>
                <th class="title-col">제목</th>
                <th class="read-col text-right"><i class="fas fa-eye"></i></th>
                <th class="writer-col"><i class="fas fa-user-edit"></i></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="content" items="${contentPage.content }">
                <tr>
                  <td class="text-right">${content.number }</td>
                  <td class="position-relative">
                  <a class="stretched-link" href="${pageContext.request.contextPath }/content/read.do?no=${content.number }&pageNo=${contentPage.currentPage}">
                    <c:out value="${content.title }" />
                  </a>
                  </td>
                  <td class="text-right">${content.readCount }</td>
                  <td>${content.writer.name }</td>
                </tr>
              </c:forEach>
            </tbody>

          </table>

        </div>
        <div class="mt-5 pagenation-container d-flex justify-content-center">
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <c:if test="${contentPage.startPage > 5}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/content/list.do?pageNo=${contentPage.startPage - 5 }">Previous</a></li>
              </c:if>
              
              <c:forEach begin="${contentPage.startPage }" end="${contentPage.endPage }" var="pNo">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/content/list.do?pageNo=${pNo}">${pNo }</a></li>
              
              </c:forEach>
              <c:if test="${contentPage.endPage < contentPage.totalPages }">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/content/list.do?pageNo=${articlePage.startPage + 5 }">Next</a></li>
              </c:if>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>

  


</body>
</html>