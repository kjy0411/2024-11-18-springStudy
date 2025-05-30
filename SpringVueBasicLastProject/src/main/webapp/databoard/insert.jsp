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
  width: 800px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
		<h3>글쓰기</h3>
		<%-- 
			defaultPrevent() => 이벤트 정지
		 --%>
		<form @submit.prevent="submitForm">
			<table class="table">
				<tr>
					<th width="15%">이름</th>
					<td width="85%">
						<input type=text name=name size=20 required="required" class="input-sm" ref="name" v-model="name">
					</td>
				</tr>
				<tr>
					<th width="15%">제목</th>
					<td width="85%">
						<input type=text name=subject size=50 required="required" class="input-sm" ref="subject" v-model="subject">
					</td>
				</tr>
				<tr>
					<th width="15%">내용</th>
					<td width="85%">
						<textarea rows="5" cols="52" name=content required="required" ref="content" v-model="content"></textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">첨부파일</th>
					<td width="85%">
						<input type="file" class="input-sm" multiple="multiple" ref="files" v-model="files" accept="upload/*">
					</td>
				</tr>
				<tr>
					<th width="15%">비밀번호</th>
					<td width="85%">
						<input type=password name=pwd size=10 required="required" class="input-sm" ref="pwd" v-model="pwd">
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
	<script>
		let insertApp=Vue.createApp({
			data(){
				return{
					name:'',
					subject:'',
					content:'',
					pwd:'',
					files:''
				}
			},
			mounted(){
				// 자동 호출 => 화면이 변경시에 한번 수행
			},
			updated(){
				
			},
			methods:{
				// 사용자 정의 함수
				submitForm(){
					/* required가 없으면 필수
					if(this.name===""){
						this.$refs.name.focus()
						return
					}
					*/
					// 데이터를 모아서 한번에 전송
					let formData=new FormData()
					formData.append("name",this.name)
					formData.append("subject",this.subject)
					formData.append("content",this.content)
					formData.append("pwd",this.pwd)
					
					// 업로드할 파일이 있는지 여부? 0이면 없다
					// files[0]
					let len=this.$refs.files.files.length
					//alert("Files Length:"+len)
					if(len>0){
						for(let i=0;i<len;i++){
							formData.append("files["+i+"]",this.$refs.files.files[i])
						}
					}
					axios.post('insert_ok.do',formData,{
						header:{
							'Context-Type':'multipart/form-data'
						}
					}).then(res=>{
						if(res.data==='yes'){
							location.href='list.do'
						}else{
							alert(res.data)
						}
					}).catch(error=>{
						console.log(error.reponse)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>