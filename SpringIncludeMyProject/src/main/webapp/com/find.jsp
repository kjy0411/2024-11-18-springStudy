<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="col-md-12">
		<form method="post" action="../com/find.do">
			<input type="text" size="15" name="fd" id="fd">
			<input type="submit" value="전송" class="btn btn-sm btn-default">
		</form>
	</div>
	<div class="col-md-12" style="min-height: 600px">
		<c:forEach var="cvo" items="${cList }">
			<div class="col-md-3">
				<div class="thumbnail" title="${cvo.name }">
					<a href="../com/detail.do?cno=${cvo.cno }">
						<img src="${cvo.poster!=null?'https://pds.saramin.co.kr/company'+=cvo.poster:'../com/logo/noimg.png' }" style="width: 180px; height: 90px">
						<div class="caption">
							<p>${cvo.name }</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="find.do?page=${startPage-1 }&fd=${fd}">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${i==curpage?"class=active":"" }><a href="find.do?page=${i }&fd=${fd}">${i }</a></li>
			</c:forEach>
			<c:if test="${endPage<totalpage }">
				<li><a href="find.do?page=${endPage+1 }&fd=${fd}">&gt;</a></li>
			</c:if>
		</ul>
		<br>
		${curpage }page / ${totalpage }pages
	</div>
</body>
</html>