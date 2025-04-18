<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px
}
.row{
	margin: 0px auto;
	width: 800px;
}
h3 {
	text-align: center
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>내용보기</h3>
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{vo.no}}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{vo.dbday}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center">{{vo.name}}</td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center">{{vo.hit}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3">{{vo.subject}}</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap; border: none; background: white;">{{vo.content}}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a :href="'/board/update/?no='+vo.no" class="btn btn-xs btn-danger">수정</a>
						<a :href="'/board/delete/?no='+vo.no" class="btn btn-xs btn-success">삭제</a>
						<a href="/board/list" class="btn btn-xs btn-warning">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="importmap">
	{
		"imports":{
			"vue":"https://unpkg.com/vue@3/dist/vue.esm-browser.js"
		}
	}
	</script>
	<script type="module">
		import {createApp} from "vue"
		const app=createApp({
			data(){
				return {
					no:${param.no},
					vo:{}
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
				async dataRecv(){
					const res=await axios.get(`/board/detail_vue/`+this.no)
					console.log(res.data)
					this.vo=res.data
				}
			}
		})
		app.mount(".container")
	</script>
</body>
</html>