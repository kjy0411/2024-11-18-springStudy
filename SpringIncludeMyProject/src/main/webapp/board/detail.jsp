<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3.5.13/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<h3 class="text-center">내용 보기</h3>
	<table class="table">
		<tr>
			<th width="20%" class="text-center">번호</th>
			<td width="30%" class="text-center">${vo.no }</td>
			<th width="20%" class="text-center">작성일</th>
			<td width="30%" class="text-center">${vo.dbday }</td>
		</tr>
		<tr>
			<th width="20%" class="text-center">이름</th>
			<td width="30%" class="text-center">${vo.name }</td>
			<th width="20%" class="text-center">조회수</th>
			<td width="30%" class="text-center">${vo.hit }</td>
		</tr>
		<tr>
			<th width="20%" class="text-center">제목</th>
			<td colspan="3">${vo.subject }</td>
		</tr>
		<tr>
			<td colspan="4" class="text-left" valign="top" height="200">
				<pre style="white-space: pre-wrap; border: none; background: white;">${vo.content }</pre>
			</td>
		</tr>
	</table>
	<div class="btn-box">
		<table class="table">
			<tr>
				<td colspan="4" class="text-right">
					<div ref="btnTr">
						<a class="btn btn-xs btn-warning">답글</a>
						<input type="button" class="btn btn-xs btn-danger" @click="pwdShow(1)" value="수정">
						<input type="button" class="btn btn-xs btn-success" @click="pwdShow(2)" value="삭제">
						<a href="list.do" class="btn btn-xs btn-warning">목록</a>
					</div>
					<div ref="pwdTr" style="display: none;">
						<input type="password" size="20" ref="pwd" v-model="pwd" placeholder="비밀번호 입력">
						<input type="button" class="btn btn-xs btn-primary" @click="pwdCheck" value="확인">
						<input type="button" class="btn btn-xs btn-danger" @click="cancel" value="취소">
						<a href="list.do" class="btn btn-xs btn-warning">목록</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<script>
		let pwdApp=Vue.createApp({
			data(){
				return {
					no:${vo.no},
					pwd:'',
					type:0
				}
			},
			methods:{
				pwdShow(no){
					this.type=no;
	                this.$refs.btnTr.style.display="none";
	                this.$refs.pwdTr.style.display="block";
				},
				cancel(){
	                this.$refs.btnTr.style.display="block";
	                this.$refs.pwdTr.style.display="none";
				},
				pwdCheck(){
					if(this.pwd===""){
						this.$refs.pwd.focus()
						return
					}
					axios.post('../board/pwd_check.do',{
							no:this.no,
							pwd:this.pwd
					}).then(response=>{
						if(response==='yes'){
							if(this.type==1){
								location.href="update.do"
							}else if(this.type==2){
								location.href="delete.do"
							}
						}else{
							alert("비밀번호가 틀렸습니다")
							this.pwd=''
							this.$refs.pwd.focus()
						}
					})
				}
			}
		}).mount('.btn-box')
	</script>
</body>
</html>