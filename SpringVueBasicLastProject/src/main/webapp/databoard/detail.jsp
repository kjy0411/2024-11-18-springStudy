<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
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
		<div class="row" id="detailApp">
			<h3>내용 보기</h3>
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{vo.no }}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{vo.dbday }}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center">{{vo.name }}</td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center">{{vo.hit }}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3">{{vo.subject }}</td>
				</tr>
				<tr v-if="vo.filecount>0">
					<th width="20%" class="text-center">첨부파일</th>
					<td colspan="3">
						<ul>
							<li v-for="(fn,index) in filename">
								<a :href="'download.do?fn='+fn">{{fn}}</a>({{filesize[index]}}Bytes)
							</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap;">{{vo.content }}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a :href="'update.do?no='+vo.no" class="btn btn-xs btn-danger">수정</a>
						<a :href="'delete.do?no='+vo.no" class="btn btn-xs btn-success">삭제</a>
						<a href="list.do" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 10px"></div>
		<div class="row" id="replyApp">
			<table class="table">
				<tr>
					<td>
						<table class="table" v-for="rvo in reply_list">
							<tr>
								<td class="text-left" :style="'padding-left: '+rvo.group_tab*20+'px'">
									<img src="../databoard/re_icon.png" v-if="rvo.group_tab>0">
									◑{{rvo.name}}&nbsp;({{rvo.dbday}})
								</td>
								<td class="text-right">
									<span v-if="rvo.id===sessionId">
										<button class="btn-xm btn-success upbtn" :id="'up'+rvo.no" @click="replyUpdateForm(rvo.no)">수정</button>
										<button class="btn-xm btn-info" @click="replyDelete(rvo.no)">삭제</button>
									</span>
									<button class="btn-xm btn-danger" v-if="sessionId!==''">댓글</button>
								</td>
							</tr>
							<tr>
								<td class="text-left" colspan="2" :style="'padding-left: '+(rvo.group_tab*20+35)+'px'">
									<pre style="white-space: pre-wrap;background-color: white;border: none;">{{rvo.msg}}</pre>
								</td>
							</tr>
							
							<tr :id="'u'+rvo.no" class="update">
								<td colspan="2">
									<textarea rows="4" cols="65" style="float: left"  :id="'umsg'+rvo.no">{{rvo.msg}}</textarea>
									<input type="button" value="댓글수정" class="btn-primary" style="float: left;height: 92px"
										@click="replyUpdate(rvo.no)">
								</td>
							</tr>
							<tr :id="'m'+rvo.no" class="insert">
								<td colspan="2">
									<textarea rows="4" cols="65" style="float: left" :id="'imsg'+rvo.no"></textarea>
									<input type="button" value="댓글쓰기" class="btn-primary" style="float: left;height: 92px"
										@click="replyInsert()">
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id!=null }">
				<table class="table">
					<tr>
						<td>
							<textarea rows="4" cols="65" style="float: left" ref="msg" v-model="msg"></textarea>
							<input type="button" value="댓글쓰기" class="btn-primary" style="float: left;height: 92px"
								@click="replyInsert()">
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
	<script>
		let detailApp=Vue.createApp({
			data(){
				return{
					no:${param.no},
					vo:{},
					filename:[],
					filesize:[]
				}
			},
			mounted(){
				axios.get('detail_vue.do',{
					params:{
						no:this.no
					}
				}).then(res=>{
					console.log(res.data)
					this.vo=res.data
					let count=res.data.filecount
					if(count>0){
						this.filename=res.data.filename.split(",")
						this.filesize=res.data.filesize.split(",")
					}
				}).catch(error=>{
					console.log(error.response)
				})
				$('.update').hide()
				$('.insert').hide()
			}
		}).mount('#detailApp')
		// 댓글 처리
		let replyApp=Vue.createApp({
			data(){
				return{
					bno:${no},
					reply_list:[],
					msg:'',
					sessionId:'${sessionId}',
					upReply:false
				}
			},
			mounted(){
				axios.get('../reply/list_vue.do',{
					params:{
						bno:this.bno
					}
				}).then(res=>{
					this.reply_list=res.data
				}).catch(error=>{
					console.log(error.response)
				})
				// 다른 JS 연결 => $(function(){})
			},
			methods:{
				replyDelete(no){
					axios.get('../reply/delete_vue.do',{
						params:{
							no:no,
							bno:this.bno
						}
					}).then(res=>{
						this.reply_list=res.data
					}).catch(error=>{
						console.log(error.response)
					})
				},
				replyUpdate(no){
					let msg=$('#umsg'+no).val()
					axios.get('../reply/update_vue.do',{
						params:{
							no:no,
							msg:msg,
							bno:this.bno
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data
						$('#u'+no).hide()
						$('.upbtn').text("수정")
						this.upReply=false
					}).catch(error=>{
						console.log(error.response)
					})
				},
				replyUpdateForm(no){
					$('.update').hide()
					$('.insert').hide()
					$('#u'+no).show()
					$('.upbtn').text("수정")
					if(this.upReply===false){
						this.upReply=true
						$('#u'+no).show()
						$('#up'+no).text("취소")
					}else{
						this.upReply=false
						$('#u'+no).hide()
						$('#up'+no).text("수정")
					}
				},
				replyInsert(){
					if(this.msg===""){
						this.$refs.msg.focus()
						return
					}
					axios.post('../reply/insert_vue.do',null,{
						params:{
							bno:this.bno,
							msg:this.msg
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data
						this.msg=''
					}).catch(error=>{
						console.log(error.response)
					})
				},
				range(tab){
					let arr=[]
					for(let i=0;i<tab;i++){
						arr[i]=i
					}
					console.log(arr)
					return arr
				}
			}
		}).mount('#replyApp')
		
	</script>
</body>
</html>