<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	jdk => 오라클 (X) => 11~14
	=> openjdk
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center" colspan="3">
				<img src="${vo.poster }" style="width: 620px;height: 250px;">
			</td>
		</tr>
		<tr>
			<td class="text-center" colspan="3"><h3>${vo.title }</h3></td>
		</tr>
		<tr>
			<td class="text-center" colspan="3" style="color: gray;">${vo.content}</td>
		</tr>
		<tr>
			<td class="text-center"><img src="../recipe/icon/a1.png"></td>
			<td class="text-center"><img src="../recipe/icon/a2.png"></td>
			<td class="text-center"><img src="../recipe/icon/a3.png"></td>
		</tr>
		<tr>
			<td class="text-center">${vo.info1 }</td>
			<td class="text-center">${vo.info2 }</td>
			<td class="text-center">${vo.info3 }</td>
		</tr>
	</table>
	<table class="table">
		<tr>
			<td><h3>[재료]</h3></td>
		</tr>
		<tr>
			<td>
				<ul>
					<c:forTokens var="d" items="${vo.data }" delims=",">
						<li>${d }</li>
					</c:forTokens>
				</ul>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><h3>[조리순서]</h3></td>
		</tr>
		<tr>
			<td>
				<c:forEach var="str" items="${mList }" varStatus="s">
					<table class="table">
						<tr>
							<td width="75%">${str }</td>
							<td class="text-center" width="25%">
								<img src="${iList[s.index] }" style="widows: 120px;height: 80px">
							</td>
						</tr>
					</table>
				</c:forEach>
			</td>
		</tr>
	</table>
	<table class="table">
		<tr>
			<td colspan="2"><h3>[레시피 작성자]</h3></td>
		</tr>
		<tr>
			<td width="20%" rowspan="2">
				<img src="${vo.chef_poster }" style="width: 100px;height: 100px" class="img-circle">
			</td>
			<td width="80%">${vo.chef }</td>
		</tr>
		<tr>
			<td width="80%"><a href="${vo.chef_profile }">${vo.chef_profile }</a></td>
		</tr>
	</table>
</body>
</html>