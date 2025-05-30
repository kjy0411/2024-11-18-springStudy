<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	@controller : JSTL / EL
		= JSP 소멸 => 새로운 JSP 생성
	@RestController : JSON = Vue/Ajax
		= JSP 변경이 없다
 -->
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
				<th class="text-center">업체명</th>
				<th class="text-center">예약일</th>
				<th class="text-center">시간</th>
				<th class="text-center">인원</th>
				<th class="text-center">등록일</th>
				<th class="text-center">비고</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center">${vo.rno }</td>
					<td class="text-center">
						<img src="https://www.menupan.com${vo.fvo.poster }" style="width: 35px;height: 35px;">
					</td>
					<td>${vo.fvo.name }</td>
					<td class="text-center">${vo.rday }</td>
					<td class="text-center">${vo.rtime }</td>
					<td class="text-center">${vo.rinwon }</td>
					<td class="text-center">${vo.dbday }</td>
					<td class="text-center">
					<c:if test="${vo.isreserve==0 }">
						<input type="button" value="예약대기" class="btn-sm btn-success">
						<a href="../reserve/reserve_delete.do?rno=${vo.rno }" class="btn-sm btn-info">취소</a>
					</c:if>
					<c:if test="${vo.isreserve==1 }">
						<input type="button" value="예약완료" class="btn-sm btn-danger">
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>