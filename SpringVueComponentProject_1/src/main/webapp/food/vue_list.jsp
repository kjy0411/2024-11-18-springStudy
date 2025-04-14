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
.container-fluid{
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
	<div class="container-fluid">
		<div class="row">
			<input type="checkbox" value="N" v-model="fd">업체명
			<input type="checkbox" value="A" v-model="fd">주소
			<input type="checkbox" value="T" v-model="fd">음식종류
			<input type="checkbox" value="M" v-model="fd">테마
			<input type="text" size="15" class="input-sm" ref="ss" v-model="ss" @keyup.enter="find()">
			<input type="button" class="btn-sm btn-primary" value="검색" @click="find()">
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="col-sm-8">
				<div class="col-md-3" v-for="vo in food_list">
				  <div class="thumbnail">
				    <a href="#">
				      <img :src="'https://www.menupan.com'+vo.poster" style="width:160px;height: 160px">
				      <div class="caption">
				        <p>{{vo.name }}</p>
				      </div>
				    </a>
				  </div>
				</div>
				<ul class="pagination">
					<li><a class="page-btn" @click="prev()">이전</a></li>
					<li><a class="page-btn" @click="next()">다음</a></li>
				</ul>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
	<script>
		let foodApp=Vue.createApp({
			data(){
				return{
					ss:'',
					fd:[],
					curpage:1,
					totalpage:0,
					food_list:[]
				}
			},
			mounted(){
			},
			methods:{
				prev(){
					this.curpage=this.curpage-1
					this.find()
				},
				next(){
					this.curpage=this.curpage+1
					this.find()
				},
				find(){
					let formData=new FormData()
					formData.append("ss",this.ss)
					formData.append("page",this.curpage)
					for(let i=0;i<this.fd.length;i++){
						formData.append("fd",this.fd[i])
					}
					axios.post('list_vue.do',formData
					).then(res=>{
						console.log(res.data)
						this.food_list=res.data.list
						this.curpage=res.date.curpage
						this.totalpage=res.date.totalpage
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container-fluid')
	</script>
</body>
</html>