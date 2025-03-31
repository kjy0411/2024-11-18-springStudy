<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 960px;
	margin: 0px auto;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="goods_detail_before.do?no=${vo.no}">
							<img src="${vo.goods_poster }" style="width: 230px;height: 200px">
							<div class="caption">
								<p>${vo.goods_name }</p>
								<p>${vo.goods_sub }</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="height: 10px"></div>
		<div class="row text-center">
			<ul class="pagination">
				<c:if test="${startPage>1 }">
					<li><a href="goods_list.do?page=${startPage-1 }">&laquo;</a></li>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<li><a ${i==curpage?"class=active":"" } href="goods_list.do?page=${i }">${i }</a></li>
				</c:forEach>
				<c:if test="${endPage<totalpage }">
					<li><a href="goods_list.do?page=${endPage+1 }">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>