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
			<table class="table">
				<tr>
					<td colspan="3" class="text-center">
						<img :src="recipe_vo.poster" style="width: 800px;height: 250px;">
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-center">
						<h3>{{recipe_vo.title}}</h3>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-center" style="color: gray;">
						{{recipe_vo.content}}
					</td>
				</tr>
				<tr>
					<td class="text-center"><img src="a1.png"></td>
					<td class="text-center"><img src="a2.png"></td>
					<td class="text-center"><img src="a3.png"></td>
				</tr>
				<tr>
					<td class="text-center">{{recipe_vo.info1}}</td>
					<td class="text-center">{{recipe_vo.info2}}</td>
					<td class="text-center">{{recipe_vo.info3}}</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td><h3>[재료]</h3></td>
				</tr>
				<tr>
					<td>
						<ul>
							<li v-for="d in data_list">{{d}}</li>
						</ul>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<table class="table" v-for="(m,index) in food_make">
							<tr>
								<td width="80%" class="text-left">
									{{m}}
								</td>
								<td width="20%" class="text-right">
									<img :src="image_list[index]" style="width: 120px;height: 80px;">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
	let detailApp=Vue.createApp({
		data(){
			return{
				no:${param.no},
				recipe_vo:{},
				image_list:[],
				food_make:[],
				data_list:[]
			}
		},
		mounted(){
			axios.get('../recipe/detail_vue.do',{
				params:{
					no:this.no
				}
			}).then(res=>{// 데이터값 받기
				console.log(res.data)
				this.recipe_vo=res.data.vo,
				this.image_list=res.data.iList,
				this.food_make=res.data.mList,
				this.data_list=res.data.vo.data.split(",")
			}).catch(error=>{// 에러 처리
				console.log(error.response)
			})
		},
		methods(){
			
		}
	}).mount('.container')
	</script>
</body>
</html>