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
			<input type="text" size="15" ref="fd" v-model="fd" v-on:keyup.enter="find()">
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			      <a :href="'../food/detail.do?fno='+vo.fno">
			        <img :src="'https://www.menupan.com'+vo.poster" style="width:160px;height: 160px">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
				<input type="button" class="btn btn-sm btn-danger" value="이전" @click="dataRevc(curpage>1?curpage-1:curpage)">
				{{curpage}} page / {{totalpage}} pages
				<input type="button" class="btn btn-sm btn-danger" value="다음" v-on:click="dataRevc(curpage<totalpage?curpage+1:curpage)">
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return {
					food_list:[],
					curpage:1,
					totalpage:0,
					fd:'마포'
				}
			},
			mounted(){
				this.dataRevc(1)
			},
			methods:{
				find(){
					this.dataRevc(1)
				},
				dataRevc(p){
					axios.get('../food/find_vue.do',{
						params:{
							page:p,
							fd:this.fd
						}
					}).then(response=>{
						console.log(response)
						this.food_list=response.data.list,
						this.curpage=response.data.curpage,
						this.totalpage=response.data.totalpage
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount(".container")
	</script>
</body>
</html>