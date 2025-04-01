<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<style type="text/css">
.container {
	width: 900px;
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 700px;
}
h3 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>수정하기</h3>
			<form method="post" action="update_ok.do">
				<table class="table">
					<tr>
						<th width="15%">이름</th>
						<td width="85%">
							<input type=text name=name size=20 required="required" class="input-sm" value="${vo.name }">
							<input type="hidden" name=no value="${param.no }">
						</td>
					</tr>
					<tr>
						<th width="15%">제목</th>
						<td width="85%">
							<input type=text name=subject size=50 required="required" class="input-sm" value="${vo.subject }">
						</td>
					</tr>
					<tr>
						<th width="15%">내용</th>
						<td width="85%">
							<textarea rows="5" cols="52" name=content required="required">${vo.content }</textarea>
						</td>
					</tr>
					<tr>
						<th width="15%">비밀번호</th>
						<td width="85%">
							<input type=password name=pwd size=10 required="required" class="input-sm">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type=submit value="수정">
							<input type=button value="취소"
							 onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>