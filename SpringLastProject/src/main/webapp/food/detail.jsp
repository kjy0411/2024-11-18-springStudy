<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>맛집 상세</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i></a></li>
                            <li class="breadcrumb-item active" aria-current="page"></li>
                            <li class="breadcrumb-item active" aria-current="page"></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row no-gutters">
                    
                    	<div class="col-12 col-sm-12">
                            <div class="related-post-area section_padding_50">
                              
                                <div class="related-post-slider owl-carousel">
                                    <!-- Single Related Post-->
                                    <c:forEach items="${list }" var="img">
	                                    <div class="single-post">
	                                        <!-- Post Thumb -->
	                                        <div class="post-thumb">
	                                            <img src="http://www.menupan.com${img }">
	                                        </div>
	                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    	<!-- 상세보기 -->
                    	<table class="table">
                    		<tr>
                    			<td width="30%" char="text-center" rowspan="8">
                    				<img src="https://www.menupan.com${vo.poster }" style="width: 270px;height: 300px">
                    			</td>
                    			<td colspan="2">
                    				<h3>${vo.name }<span style="color: orange">${vo.score }</span></h3>
                    			</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">주소</td>
                    			<td width="45%">${vo.address }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">전화</td>
                    			<td width="45%">${vo.phone }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">음식종류</td>
                    			<td width="45%">${vo.type }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">가격대</td>
                    			<td width="45%">${vo.price }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">주차</td>
                    			<td width="45%">${vo.parking }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">영업시간</td>
                    			<td width="45%">${vo.time }</td>
                    		</tr>
                    		<tr>
                    			<td width="25%">테마</td>
                    			<td width="45%">${vo.theme }</td>
                    		</tr>
                    	</table>
                    	<table>
                    		<tr>
                    			<td>${vo.content }</td>
                    		</tr>
                    	</table>
                    </div>
                </div>
            </div>
            <div style="height: 10px"></div>
			<div class="row" id="replyApp">
				<%-- 댓글 : Vue --%>
				<!-- Comment Area Start -->
                <div class="comment_area section_padding_50 clearfix" style="width: 650px">
					<ol>
                        <!-- Single Comment Area -->
                        <li class="single_comment_area" v-for="rvo in reply_list">
                            <div class="comment-wrapper d-flex" v-if="rvo.group_step===0">
                                <!-- Comment Meta -->
                                <div class="comment-author">
                                    <img :src="rvo.sex==='남자'?'../img/man.png':'../img/woman.png'">
                                </div>
                                <!-- Comment Content -->
                                <div class="comment-content">
                                    <span class="comment-date text-muted">{{rvo.dbday}}</span>
                                    <h5>{{rvo.username}}</h5>
                                    <p :id="'msg'+rvo.no">{{rvo.msg}}</p>
                                    <button v-if="sessionId===rvo.userid" class="btn-xs btn-danger update" style="margin-left: 2px" :id="'u'+rvo.no" @click="replyUpdateForm(rvo.no)">수정</button>
                                    <button v-if="sessionId===rvo.userid" class="btn-xs btn-info" style="margin-left: 2px" @click="replyDelete(rvo.no)">삭제</button>
                                    <button v-if="sessionId!==''" class="btn-xs btn-success insert" style="margin-left: 2px" :id="'i'+rvo.no" @click="replyReplyInsertForm(rvo.no)">댓글</button>
                                    <%-- 수정창 --%>
                                    <table class="table ups" style="display: none;" :id="'up'+rvo.no">
			                    		<tr>
			                    			<td>
			                    				<textarea rows="4" cols="45" style="float: left;" :id="'umsg'+rvo.no">{{rvo.msg}}</textarea>
			                    				<input type="button" class="btn-sm btn-primary" value="수정"
			                    					style="float: left;color: white;width: 80px;height: 98px" @click="replyUpdate(rvo.no)">
			                    			</td>
			                    		</tr>
			                    	</table>
                                    <%-- 대댓글창 --%>
                                    <table class="table ins" style="display: none" :id="'in'+rvo.no">
			                    		<tr>
			                    			<td>
			                    				<textarea rows="4" cols="45" style="float: left;" :id="'imsg'+rvo.no"></textarea>
			                    				<input type="button" class="btn-sm btn-primary" value="댓글"
			                    					style="float: left;color: white;width: 80px;height: 98px" @click="replyReplyInsert(rvo.no)">
			                    			</td>
			                    		</tr>
			                    	</table>
                                </div>
                            </div>
                            <ol class="children" v-if="rvo.group_step>0">
                                <li class="single_comment_area">
                                    <div class="comment-wrapper d-flex">
                                        <!-- Comment Meta -->
		                                <div class="comment-author">
		                                    <img :src="rvo.sex==='남자'?'../img/man.png':'../img/woman.png'">
		                                </div>
		                                <!-- Comment Content -->
		                                <div class="comment-content">
		                                    <span class="comment-date text-muted">{{rvo.dbday}}</span>
		                                    <h5>{{rvo.username}}</h5>
		                                    <p>{{rvo.msg}}</p>
		                                    <button v-if="sessionId===rvo.userid" class="btn-xs btn-danger update" style="margin-left: 2px" :id="'u'+rvo.no" @click="replyUpdateForm(rvo.no)">수정</button>
		                                    <button v-if="sessionId===rvo.userid" class="btn-xs btn-info" style="margin-left: 2px" @click="replyDelete(rvo.no)">삭제</button>
		                                    <%-- 수정창 --%>
		                                    <table class="table ups" style="display: none;" :id="'up'+rvo.no">
					                    		<tr>
					                    			<td>
					                    				<textarea rows="4" cols="45" style="float: left;" :id="'umsg'+rvo.no">{{rvo.msg}}</textarea>
					                    				<input type="button" class="btn-sm btn-primary" value="수정"
					                    					style="float: left;color: white;width: 80px;height: 98px" @click="replyUpdate(rvo.no)">
					                    			</td>
					                    		</tr>
					                    	</table>
		                                </div>
                                    </div>
                                </li>
                            </ol>
                        </li>
                    </ol>
                </div>
                <div style="height: 10px"></div>
                <c:if test="${sessionScope.userid!=null }">
	                <!-- Leave A Comment -->
	                <div class="leave-comment-area section_padding_50 clearfix">
	                    <div class="comment-form">
	                    	<table class="table">
	                    		<tr>
	                    			<td>
	                    				<textarea rows="4" cols="70" style="float: left;" ref="msg" v-model="msg"></textarea>
	                    				<input type="button" class="btn-sm btn-primary" value="댓글"
	                    					style="float: left;color: white;width: 80px;height: 98px" @click="replyInsert()">
	                    			</td>
	                    		</tr>
	                    	</table>
	                    </div>
	                </div>
                </c:if>
			</div>
		</div>
	</section>
    <script>
	    let replyApp=Vue.createApp({
			data(){
				return {
					reply_list:[],
					cno:${vo.fno},
					type:2,
					curpage:1,
					sessionId:'${sessionId}',
					totalpage:0,
					startPage:0,
					endPage:0,
					msg:'',
					upReply:false,
					uno:0,
					inReply:false
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
				replyDelete(no){
					axios.get("../comment/delete_vue.do",{
						params:{
							no:no,
							cno:this.cno,
							type:this.type
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
					}).catch(error=>{
						console.log(error.response)
					})
				},
				replyReplyInsert(no){
					let msg=$('#imsg'+no).val()
					if(msg.trim()===""){
						$('#imsg'+no).focus()
						return
					}
					
					axios.post("../comment/reply_insert_vue.do",null,{
						params:{
							no:no,
							cno:this.cno,
							type:this.type,
							msg:msg,
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
						$('#imsg'+no).val("")
						$('#in'+no).hide()
						$('#i'+no).text('댓글')
	    				this.inReply=false
					}).catch(error=>{
						console.log(error.response)
					})
				},
				replyReplyInsertForm(no){
					$('.ins').hide()
					$('.insert').text('댓글')
					$('.ups').hide()
					$('.update').text('수정')
					if(this.inReply){
	    				this.inReply=false
						$('#in'+no).hide()
						$('#i'+no).text('댓글')
					}else{
						this.inReply=true
						$('#in'+no).show()
						$('#i'+no).text('취소')
					}
				},
				replyUpdate(no){
					let msg=$('#umsg'+no).val()
					if(msg.trim()===''){
						$('#umsg'+no).focus()
						return
					}
					
					axios.post("../comment/update_vue.do",null,{
						params:{
							no:no,
							cno:this.cno,
							type:this.type,
							msg:msg,
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
						$('#umsg'+no).val("")
						$('#up'+no).hide()
						$('#u'+no).text('수정')
	    				this.upReply=false
					}).catch(error=>{
						console.log(error.response)
					})
				},
				replyUpdateForm(no){
					$('.ins').hide()
					$('.insert').text('댓글')
					$('.ups').hide()
					$('.update').text('수정')
					if(this.upReply){
	    				if(this.uno===no){
		    				this.upReply=false
		    				let msg=$('#msg'+no).text()
		    				console.log(msg)
	    					$('#umsg'+no).text(msg)
	    					$('#up'+no).hide()
	    					$('#u'+no).text('수정')
	    				}else{
	    					$('#umsg'+this.uno).text($('#msg'+this.uno).text())
	    					$('#up'+this.uno).hide()
	    					$('#u'+this.uno).text('수정')
	    					$('#up'+no).show()
	    					$('#u'+no).text('취소')
	    					this.uno=no
	    				}
					}else{
						this.upReply=true
						$('#up'+no).show()
						$('#u'+no).text('취소')
						this.uno=no
					}
				},
				replyInsert(){
					/*
						데이터 읽기 쓰기=> data()안에 있는 변수
									=> v-model
						해당 태그 제어
						 => ref
						 => $refs.ref명...
					*/
					if(this.msg===''){
						this.$refs.msg.focus()
						return
					}
					// 데이터를 서버로 전송
					axios.post("../comment/insert_vue.do",null,{
						params:{
							cno:this.cno,
							type:this.type,
							msg:this.msg,
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
						this.msg=''
					}).catch(error=>{
						console.log(error.response)
					})
				},
				dataRecv(){
					axios.get('../comment/list_vue.do',{
						params:{
							page:this.curpage,
							cno:this.cno,
							type:this.type
						}
					}).then(res=>{
						console.log(res.data)
						this.reply_list=res.data.list
						this.curpage=res.data.curpage
						this.totalpage=res.data.totalpage
						this.startPage=res.data.startPage
						this.endPage=res.data.endPage
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('#replyApp')
    </script>
</body>
</html>