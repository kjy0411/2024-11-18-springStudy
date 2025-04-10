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
			<div class="text-center">
				<input type="button" value="한식" class="btn-lg btn-success" @click="food(1)">
				<input type="button" value="일식" class="btn-lg btn-info" @click="food(2)">
				<input type="button" value="중식" class="btn-lg btn-warning" @click="food(3)">
				<input type="button" value="양식" class="btn-lg btn-danger" @click="food(4)">
				<input type="button" value="분식" class="btn-lg btn-primary" @click="food(5)">
			</div>
		</div>
		<div style="height: 10px"></div>		
		<div class="row">
			<h3 class="text-center">{{title}}</h3>
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
			<ul class="pagination">
				<li v-if="startPage>1"><a class="page-btn" @click="prev()">&lt;</a></li>
				<li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a class="page-btn" @click="pageChange(i)">{{i}}</a></li>
				<li v-if="endPage<totalpage"><a class="page-btn" @click="next()">&gt;</a></li>
			</ul>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return {
					food_list:[],
					curpage:1,
					totalpage:0,
					startPage:0,
					endPage:0,
					title:'한식',
					type:1
				}
			},
			mounted(){
				this.dataRevc()
			},
			methods:{
				prev(){
					this.curpage=this.startPage-1
					this.dataRevc()
				},
				next(){
					this.curpage=this.endPage+1
					this.dataRevc()
				},
				pageChange(i){
					this.curpage=i
					this.dataRevc()
				},
				food(type){
					this.curpage=1
					this.type=type
					this.dataRevc()
				},
				range(start,end){
					let arr=[]
					let len=end-start
					for(let i=0;i<=len;i++){
						arr[i]=start
						start++
					}
					return arr
				},
				dataRevc(){
					axios.get('../food/list_vue.do',{
						params:{
							page:this.curpage,
							type:this.type
						}
					}).then(response=>{
						console.log(response)
						this.food_list=response.data.list,
						this.curpage=response.data.curpage,
						this.totalpage=response.data.totalpage
						this.startPage=response.data.startPage
						this.endPage=response.data.endPage
						this.title=response.data.title
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount(".container")
	</script>
</body>
</html>