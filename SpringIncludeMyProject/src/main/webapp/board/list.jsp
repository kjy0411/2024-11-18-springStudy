<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="min-height: 500px">
		<h3 class="text-center">Spring를 이용한 게시판</h3>
		<table class="table">
			<tr>
				<td><a href="insert.do" class="btn btn-sm btn-primary">새글</a></td>
			</tr>
		</table>
		<table class="table">
			<tr>
				<th width="10%" class="text-center">번호</th>
				<th width="45%" class="text-center">제목</th>
				<th width="15%" class="text-center">이름</th>
				<th width="20%" class="text-center">작성일</th>
				<th width="10%" class="text-center">조회수</th>
			</tr>
			<c:forEach var="vo" items="${bList }">
				<tr>
					<td width="10%" char="text-center">${vo.no }</td>
					<td width="45%"><a href="detail.do?no=${vo.no }">${vo.subject }</a></td>
					<td width="15%" char="text-center">${vo.name }</td>
					<td width="20%" char="text-center">${vo.dbday }</td>
					<td width="10%" char="text-center">${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="list.do?page=${startPage-1 }">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${i==curpage?"class=active":"" }><a href="list.do?page=${i }">${i }</a></li>
			</c:forEach>
			<c:if test="${endPage<totalpage }">
				<li><a href="list.do?page=${endPage+1 }">&gt;</a></li>
			</c:if>
		</ul>
		<br>
		${curpage }page / ${totalpage }pages
	</div>
</body>
</html>