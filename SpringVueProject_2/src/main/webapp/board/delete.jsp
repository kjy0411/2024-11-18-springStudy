<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	RestFul => React
		SELECT = GET	=> @GetMapping
		INSERT = POST	=> @PostMapping
		UPDATE = PUT	=> @PutMapping
		DELETE = DELETE	=> @DeleteMapping
		====================================== 통합 => @RequestMapping
 -->
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
	width: 300px;
}
h3 {
	text-align: center
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>삭제하기</h3>
			<table class="table">
				<tr>
					<td>
						비밀번호:<input type="password" size="20" ref="pwd" v-model="pwd">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<button class="btn-sm btn-danger" @click="del()">삭제</button>
						<button class="btn-sm btn-primary" onclick="javascript:history.back()">취소</button>
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
					pwd:'',
				}
			},
			methods:{
				del(){
					if(this.pwd===''){
						this.$refs.pwd.focus()
						return
					}
					this.dataRecv()
				},
				async dataRecv(){
					const res=await axios.delete(`/board/delete_vue/`+this.no+`/`+this.pwd)
					console.log(res.data)
					if(res.data.msg==='yes'){
						location.href="/board/list"
					}else{
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