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
 margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 960px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-danger">등록</a>
						<a href="../member/login.do" class="btn btn-sm btn-default" v-if="sessionId==''">로그인</a>
						<a href="../member/logout.do" class="btn btn-sm btn-default" v-if="sessionId!=''">로그아웃</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<th width="10%" class="text-center">번호</th>
					<th width="40%" class="text-center">제목</th>
					<th width="15%" class="text-center">이름</th>
					<th width="15%" class="text-center">작성일</th>
					<th width="10%" class="text-center">조회수</th>
					<th width="10%" class="text-center">댓글수</th>
				</tr>
				<tr v-for="vo in board_list">
					<td width="10%" class="text-center">{{vo.no}}</td>
					<td width="40%" class="text-left"><a :href="'../databoard/detail.do?no='+vo.no">{{vo.subject}}</a></td>
					<td width="15%" class="text-center">{{vo.name}}</td>
					<td width="15%" class="text-center">{{vo.dbday}}</td>
					<td width="10%" class="text-center">{{vo.hit}}</td>
					<td width="10%" class="text-center">{{vo.replycount}}</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		let ListApp=Vue.createApp({
			data(){
				return{
					board_list:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0,
					sessionId:'${sessionId}'
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
				dataRecv(){
					/*
						post로 params를 보낼 경우
						axios.post("list_vue.do",null,{
							params:{
								
							}
						})
						처럼 두번째를 null로 처리후 세번째로 보내야한다
					*/
					axios.get("list_vue.do",{
						params:{
							page:this.curpage
						}
					}).then(res=>{
						console.log(res)
						this.board_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>