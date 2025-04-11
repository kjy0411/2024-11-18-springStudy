<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 100%;
}
p{
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="checkbox" value="사과" v-model="f">사과
			<input type="checkbox" value="배" v-model="f">배
			<input type="checkbox" value="딸기" v-model="f">빨기
			<input type="checkbox" value="수박" v-model="f">수박
			<input type="checkbox" value="멜론" v-model="f">멜론
			<br>
			<input type="text" size="15" v-model="ss">
			<input type="button" value="전송" @click="send()">
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					f:[],
					ss:''
				}
			},
			methods:{
				send(){
					let formData=new FormData()
					formData.append("ss",this.ss)
					formData.append("no",123)
					for(let i=0;i<this.f.length;i++){
						formData.append("f",this.f[i])
					}
					console.log(formData)
					axios.post('vue_check2_vue.do',formData
					).then(res=>{
						console.log(res.data)
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>