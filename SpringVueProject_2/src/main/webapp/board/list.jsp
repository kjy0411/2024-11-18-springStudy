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
			<h3>{{msg}}</h3>
			<table class="table">
				<tr>
					<td><a href="/board/insert" class="btn btn-sm btn-danger">새글</a></td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th width="10%" class="text-center">번호</th>
					<th width="45%" class="text-center">제목</th>
					<th width="15%" class="text-center">이름</th>
					<th width="20%" class="text-center">작성일</th>
					<th width="10%" class="text-center">조회수</th>
				</tr>
				<tr v-for="vo in list">
					<td width="10%" char="text-center">{{vo.no}}</td>
					<td width="45%"><a :href="'/board/detail/?no='+vo.no">{{vo.subject}}</a></td>
					<td width="15%" char="text-center">{{vo.name}}</td>
					<td width="20%" char="text-center">{{vo.dbday}}</td>
					<td width="10%" char="text-center">{{vo.hit}}</td>
				</tr>
			</table>
		</div>
		<div style="height: 10px"></div>
		<div class="row text-center">
			<ul class="pagination">
				<li v-if="startPage>1"><a class="nav-link" @click="prev()">&lt;</a></li>
				<li v-for="i in range(startPage,endPage)" :class="i===curpage?'active':''"><a class="nav-link" @click="pageChange(i)">{{i}}</a></li>
				<li v-if="endPage<totalpage"><a class="nav-link" @click="next()">&gt;</a></li>
			</ul>
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
					list:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0,
					msg:''
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
				async dataRecv(){
					const res=await axios.get(`/board/list_vue/`+this.curpage)
					console.log(res)
					this.list=res.data.list
					this.curpage=res.data.curpage
					this.totalpage=res.data.totalpage
					this.startPage=res.data.startPage
					this.endPage=res.data.endPage
					this.msg=res.data.msg
				},
				range(start,end){
					let arr=[]
					let len=end-start
					for(let i=0;i<=len;i++){
						arr[i]=start
						start++
					}
					return arr
				},
				prev(){
					this.curpage=this.startPage-1
					this.dataRecv()
				},
				next(){
					this.curpage=this.endPage+1
					this.dataRecv()
				},
				pageChange(page){
					this.curpage=page
					this.dataRecv()
				}
			}
		})
		app.mount(".container")
	</script>
</body>
</html>