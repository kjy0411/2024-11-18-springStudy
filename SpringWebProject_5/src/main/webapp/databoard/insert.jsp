<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0;
$(function(){
	$('#addBtn').click(function(){
		$('#user-table tbody').append(
			'<tr id="f'+fileIndex+'">'
			+'<td width=30%>File '+(fileIndex+1)+'</td>'
			+'<td width=70%><input type=file size=20 name="files['+fileIndex+']"></td>'
			+'</tr>'
		)
		fileIndex++
	})
	$('#canBtn').click(function(){
		if(fileIndex>0){
			$('#f'+(fileIndex-1)).remove()
			fileIndex--
		}
	})
})
</script>
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
		<h3>글쓰기</h3>
		<!-- 
			파일 업로드 : enctype="multipart/form-data"
				=> 프로토콜
		 -->
		<form method="post" action="insert_ok.do" enctype="multipart/form-data">
			<table class="table">
				<tr>
					<th width="15%">이름</th>
					<td width="85%">
						<input type=text name=name size=20 required="required" class="input-sm">
					</td>
				</tr>
				<tr>
					<th width="15%">제목</th>
					<td width="85%">
						<input type=text name=subject size=50 required="required" class="input-sm">
					</td>
				</tr>
				<tr>
					<th width="15%">내용</th>
					<td width="85%">
						<textarea rows="5" cols="52" name=content required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table class="table">
							<tr>
								<td class="text-right">
									<input type="button" class="btn btn-xs btn-primary" value="추가" id="addBtn">
									<input type="button" class="btn btn-xs btn-danger" value="취소" id="canBtn">
								</td>
							</tr>
						</table>
						<table class="table" id="user-table">
							<tbody></tbody>
						</table>
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
						<input type=submit value="글쓰기">
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