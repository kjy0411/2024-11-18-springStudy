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
p {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
pre{
	white-space: normal;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td rowspan="7" width="30%">
						<img :src="'https://www.menupan.com'+food_vo.poster" style="width: 300px;height: 300px" v-if="food_vo.poster">
					</td>
					<td colspan="2" width="70%">
						<h3>{{food_vo.name}}(<span style="color: orange;">{{food_vo.score}}</span>)</h3>
					</td>
				</tr>
				<tr>
					<td width="20%">타입</td>
					<td width="50%">
						{{food_vo.type}}
					</td>
				</tr>
				<tr>
					<td width="20%">테마</td>
					<td width="50%">
						{{food_vo.theme}}
					</td>
				</tr>
				<tr>
					<td width="20%">가격</td>
					<td width="50%">
						{{food_vo.price}}
					</td>
				</tr>
				<tr>
					<td width="20%">전화번호</td>
					<td width="50%">
						{{food_vo.phone}}
					</td>
				</tr>
				<tr>
					<td width="20%">시간</td>
					<td width="50%">
						{{food_vo.time}}
					</td>
				</tr>
				<tr>
					<td width="20%">주차장</td>
					<td width="50%">
						{{food_vo.parking}}
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td>
						<pre>{{food_vo.content}}</pre>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		let detailApp=Vue.createApp({
			data(){
				return{
					fno:${param.fno},
					food_vo:{}
				}
			},
			mounted(){
				axios.get('../food/detail_vue.do',{
					params:{
						fno:this.fno
					}
				}).then(res=>{
					console.log(res.data)
					this.food_vo=res.data
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods(){
				
			}
		}).mount('.container')
	</script>
</body>
</html>