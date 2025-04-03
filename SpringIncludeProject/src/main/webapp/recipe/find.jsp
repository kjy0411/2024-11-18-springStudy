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
	<div class="col-md-12 ">
		<form method="post" action="../recipe/find.do">
			<input type="text" class="input-sm" size="15" name="fd" id="fd" value="${fd }" required="required">
			<input type="submit" value="검색" class="btn btn-sm btn-defalut">
		</form>
	</div>
	<div class="col-md-12" style="min-height: 500px;">
		<c:if test="${rList.size()==0 }">
			<h3 class="text-center">"${fd }"로 검색된 레시피가 없습니다</h3>
		</c:if>
		<c:forEach var="vo" items="${rList }">
			<div class="col-md-3">
				<div class="thumbnail">
					<a href="../recipe/detail.do?no=${vo.no }"> <img src="${vo.poster }" title="${vo.title }" style="width: 180px; height: 150px">
						<div class="caption">
							<p>${vo.title }</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-md-12 text-center">
		<ul class="pagination">
			<c:if test="${startPage>1 }">
				<li><a href="../recipe/find.do?page=${startPage-1 }&fd=${fd}">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li ${i==curpage?"class=active":"" }><a href="../recipe/find.do?page=${i }&fd=${fd}">${i }</a></li>
			</c:forEach>
			<c:if test="${endPage<totalpage }">
				<li><a href="../recipe/find.do?page=${endPage+1 }&fd=${fd}">&gt;</a></li>
			</c:if>
		</ul>
		<br>
		${curpage }page / ${totalpage }pages
	</div>
</body>
</html>