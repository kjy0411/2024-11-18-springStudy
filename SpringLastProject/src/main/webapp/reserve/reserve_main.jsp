<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
    1. 예약일 = 오늘 ~
    2. 시간 - 임의로 설정
    3. 인원 = 1~10 /단체
    달력 : full => link
        <link href='https://cdn.jsdelivr.net/npm/@fullcalendar/icalendar@5.11.3/main.css' rel='stylesheet' />
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='https://cdn.jsdelivr.net/npm/@fullcalendar/icalendar@5.11.3/main.css' rel='stylesheet' />
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
<style type="text/css">
.food-click{
	cursor: pointer;
}
.food-click:hover{
	background-color: gray;
}
</style>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>예약</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->
    <section class="single_blog_area section_padding_80" id="reserveApp">
        <div class="container">
            <div class="row justify-content-center">
            	<div class="col-12 col-lg-12">
            		<div class="row no_gutters">
            			<table class="table">
            				<tr>
            					<td width="20%" height="500">
            						<table class="table">
            							<tr>
            								<td colspan="2">
            									<h4>맛집 정보</h4>
            								</td>
            							</tr>
            						</table>
            						<div style="overflow-y:auto;height: 500px">
            							<table class="table">
            								<tr>
            									<th class="text-center"></th>
            									<th class="text-center">업체명</th>
            								</tr>
            								<tr v-for="vo in food_list" class="food-click" @click="foodSelect(vo.fno,vo.poster,vo.name)">
            									<td class="text-center">
            										<img :src="'https://www.menupan.com'+vo.poster" style="width: 35px;height: 35px">
            									</td>
            									<td class="text-center">{{vo.name}}</td>
            								</tr>
            							</table>
            						</div>
            					</td>
            					<td width="45%" height="500">
            						<table class="table">
            							<tr>
            								<td colspan="2">
            									<h4>예약일 정보</h4>
            								</td>
            							</tr>
            							<tr>
            								<td>
            									<div id="calendar" v-show="isDate"></div>
            								</td>
            							</tr>
            						</table>
            					</td>
            					<td width="35%" height="650" rowspan="2">
            						<table class="table">
            							<tr>
            								<td colspan="2" @click=test()>
            									<h4>예약 정보</h4>
            								</td>
            							</tr>
            							<tr>
            								<td colspan="2" class="text-center">
           										<img :src="image" style="width: 200px;height: 150px">
            								</td>
            							</tr>
            							<tr>
            								<td width="40%" class="text-center">업체명</td>
            								<td width="60%" class="text-center">{{name}}</td>
            							</tr>
            							<tr>
            								<td width="40%" class="text-center">예약일</td>
            								<td width="60%" class="text-center">{{day}}</td>
            							</tr>
            							<tr>
            								<td width="40%" class="text-center">시간</td>
            								<td width="60%" class="text-center">{{time}}</td>
            							</tr>
            							<tr>
            								<td width="40%" class="text-center">인원</td>
            								<td width="60%" class="text-center">{{inwon}}</td>
            							</tr>
            							<tr v-show="isReserveBtn">
            								<td colspan="2" class="text-center">
            									<button class="btn-lg btn-primary" @click="reserve()">예약</button>
            								</td>
            							</tr>
            						</table>
            					</td>
            				</tr>
            				<tr>
            					<td width="30%" height="150">
            						<table class="table">
            							<tr>
            								<td colspan="2">
            									<h4>시간 정보</h4>
            								</td>
            							</tr>
            							<tr v-show="isTime">
            								<td class="text-center">
            									<span class="btn btn-sm btn-success" v-for="t in time_list"
            										style="margin: 2px" @click="timeSelect(t)">{{t}}</span>
            								</td>
            							</tr>
            						</table>
            					</td>
            					<td width="35%" height="150">
            						<table class="table">
            							<tr>
            								<td colspan="2">
            									<h4>인원 정보</h4>
            								</td>
            							</tr>
            							<tr v-show="isInwon">
            								<td class="text-center">
            									<span class="btn btn-sm btn-danger" v-for="i in inwon_list"
            										style="margin: 2px" @click="inwonSelect(i)">{{i}}</span>
            								</td>
            							</tr>
            						</table>
            					</td>
            				</tr>
            			</table>
            		</div>
            	</div>
            </div>
        </div>
    </section>
    <script>
    	/*
    		FullCalendar.Calendar(
    			this
    		)
    	*/
    	let reserveApp=Vue.createApp({
    		data(){
    			return {
    				food_list:[],
    				image:'../img/noimg.png',
    				fno:0,
    				name:'',
    				isDate:false,
    				day:'',
    				time:'',
    				inwon:'',
    				time_list:[],
    				inwon_list:[],
    				isTime:false,
    				isInwon:false,
    				isReserveBtn:false
    			}
    		},
    		mounted(){
    			this.dataRecv()
    			// 코딩 테스트 : 자바스크립트
    			let date=new Date()
    			let year=date.getFullYear()
    			let month=("0"+(1+date.getMonth())).slice(-2)
    			let day=("0"+date.getDate()).slice(-2)
    			//console.log(year+'년 '+month+'월 '+day+'일')
    			let _this=this
    			// 달력
    			document.addEventListener('DOMContentLoaded',function(){
    				let calendarEl=document.getElementById("calendar")
    				let calendar=new FullCalendar.Calendar(calendarEl,{
    					initialView:'dayGridMonth',
    					height:450,
    					width:400,
    					validRange:{
    						start:year+"-"+month+"-"+day
    					},
    					themeSystem:'bootstrap',
    					editable:true,
    					dropable:true,
    					// 이벤트 => 날짜 클릭
    					dateClick:((info)=>{
    						//alert("Click Date:"+info.dateStr)
    						_this.day=info.dateStr
    						_this.isTime=true
    					})
    				})
	   				calendar.render()
    			})
    		},
    		methods:{
    			reserve(){
    				axios.post("../reserve/reserve_vue_ok.do",null,{
    					params:{
    						fno:this.fno,
    						rday:this.day,
    						rtime:this.time,
    						rinwon:this.inwon
    					}
    				}).then(res=>{ // onSuccess => React
    					console.log(res.data)
    					if(res.data==='yes'){
    						//마이페이지 이동
    						location.href="../mypage/reserve_list.do"
    					}else{
    						//에러메세지 전송
    						alert(res.data)
    					}
    						
    				}).catch(error=>{ // onError => React
    					console.log(error.response)
    				})
    			},
    			inwonSelect(i){
    				this.inwon=i
    				this.isReserveBtn=true
    			},
    			timeSelect(t){
    				this.time=t
    				this.isInwon=true
    			},
    			foodSelect(fno,poster,name){
    				this.fno=fno
    				this.image='https://www.menupan.com'+poster
    				this.name=name
    				this.isDate=true
    			},
    			dataRecv(){
    				axios.get("../reserve/main_vue.do")
	    			.then(res=>{
	    				console.log(res.data)
	    				this.food_list=res.data.list
	    				this.time_list=res.data.time
	    				this.inwon_list=res.data.inwon
	    			}).catch(error=>{
	    				console.log(error.response)
	    			})
    			}
    		}
    	}).mount("#reserveApp")
    </script>
</body>
</html>