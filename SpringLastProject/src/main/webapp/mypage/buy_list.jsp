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
	<div class="row justify-content-center">
		<table class="table">
			<tr>
				<th class="text-center">번호</th>
				<th></th>
				<th class="text-center">상품명</th>
				<th class="text-center">가격</th>
				<th class="text-center">수량</th>
				<th class="text-center">등록일</th>
				<th class="text-center">비고</th>
			</tr>
			<c:if test="${list.size()==0 }">
				<tr>
					<td class="text-center" colspan="7">
						<h3>구매한 상품 기록이 없습니다</h3>
					</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center">${vo.cno }</td>
					<td class="text-center">
						<img src="${vo.gvo.goods_poster }" style="width: 35px;height: 35px;">
					</td>
					<td><p style="max-width: 400px;">${vo.gvo.goods_name }</p></td>
					<td class="text-center">${vo.gvo.goods_price }</td>
					<td class="text-center">${vo.account }</td>
					<td class="text-center">${vo.dbday }</td>
					<td class="text-center">
						<a href="../goods/detail.do?no=${vo.gno }" class="btn-sm btn-info">상세보기</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>