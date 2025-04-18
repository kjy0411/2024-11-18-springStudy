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
			<h3>수정하기</h3>
			<table class="table">
			<tr>
				<th width="15%">이름</th>
				<td width="85%">
					<input type=text size=20 class="input-sm" ref="name" v-model="name">
				</td>
			</tr>
			<tr>
				<th width="15%">제목</th>
				<td width="85%">
					<input type=text size=50 class="input-sm" ref="subject" v-model="subject">
				</td>
			</tr>
			<tr>
				<th width="15%">내용</th>
				<td width="85%">
					<textarea rows="5" cols="52" ref="content" v-model="content"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%">비밀번호</th>
				<td width="85%">
					<input type=password size=10 class="input-sm" ref="pwd" v-model="pwd">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type=button value="수정하기" class="btn-sm btn-danger" @click="update()">
					<input type=button value="취소" class="btn-sm btn-primary" onclick="javascript:history.back()">
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
					name:'',
					subject:'',
					content:'',
					pwd:'',
					msg:''
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
				async dataRecv(){
					const res=await axios.get(`/board/update_vue/`+this.no)
					this.name=res.data.name
					this.subject=res.data.subject
					this.content=res.data.content
				},
				async update(){
					if(this.name===''){
						this.$refs.name.focus()
						return
					}
					if(this.subject===''){
						this.$refs.subject.focus()
						return
					}
					if(this.content===''){
						this.$refs.content.focus()
						return
					}
					if(this.pwd===''){
						this.$refs.pwd.focus()
						return
					}
					const res=await axios.put(`/board/update_ok_vue`,{
						no:this.no,
						name:this.name,
						subject:this.subject,
						content:this.content,
						pwd:this.pwd
					})
					console.log(res)
					if(res.data.msg==='yes'){
						location.href="/board/detail/?no="+this.no
					}else if(res.data.msg==="no"){
						alert("비밀번호가 틀립니다")
						this.pwd=''
						this.$refs.pwd.focus()
					}
				}
			}
		})
		app.mount(".container")
	</script>
</body>
</html>