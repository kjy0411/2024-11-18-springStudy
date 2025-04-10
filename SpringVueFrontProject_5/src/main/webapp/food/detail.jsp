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
.hide{
	display: none;
}
.show{
	display: '';
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td rowspan="8" width="30%">
						<img :src="'https://www.menupan.com'+food_vo.poster" style="width: 300px;height: 300px" v-if="food_vo.poster">
					</td>
					<td colspan="2" width="70%">
						<h3>{{food_vo.name}}(<span style="color: orange;">{{food_vo.score}}</span>)</h3>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">타입</td>
					<td width="50%">
						{{food_vo.type}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">테마</td>
					<td width="50%">
						{{food_vo.theme}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">가격</td>
					<td width="50%">
						{{food_vo.price}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">전화번호</td>
					<td width="50%">
						{{food_vo.phone}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">시간</td>
					<td width="50%">
						{{food_vo.time}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">주차장</td>
					<td width="50%">
						{{food_vo.parking}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">주소</td>
					<td width="50%">
						{{food_vo.address}}
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td>
						<pre>{{food_vo.content}}</pre>
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<a @click="map()" class="btn-sm btn-success">지도</a>
						<a href="javascript:history.back()" class="btn-sm btn-primary">목록</a>
					</td>
				</tr>
			</table>
			<div style="height: 10px"></div>
			<div class="row">
				<div id="map" style="width:100%;height:350px;" :class="map_state?'show':'hide'"></div>
			</div>
		</div>
	</div>
	<script>
		let detailApp=Vue.createApp({
			data(){
				return{
					fno:${param.fno},
					food_vo:{},
					map_state:true
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
					if(window.kakao && window.kakao.maps){
						this.initMap()
					}else{
						this.addScript()
					}
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods:{
				map(){
					this.map_state=!this.map_state
				},
				addScript(){
					const script=document.createElement("script")
					script.onload=()=>kakao.maps.load(this.initMap)
					script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f5c499003a5ed7ef16b41579b19a32a3&libraries=services"
	    		    document.head.appendChild(script)
				},
				initMap(){
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					    mapOption = {
					        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
					        level: 3 // 지도의 확대 레벨
					    };  
	
					// 지도를 생성합니다    
					var map = new kakao.maps.Map(mapContainer, mapOption); 
	
					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();
					var name=this.food_vo.name;
					// 주소로 좌표를 검색합니다
					geocoder.addressSearch(this.food_vo.address, function(result, status) {
	
					    // 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });
							
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
					        });
					        infowindow.open(map, marker);
	
					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
					        
					        searchNearbyFacilities(coords);
					    } 
					});
					function searchNearbyFacilities(coords) {
					    var ps = new kakao.maps.services.Places();

					    // "편의점" 키워드로 반경 500m 내 검색
					    ps.keywordSearch('화장실', function (data, status) {
					        if (status === kakao.maps.services.Status.OK) {
					            for (var i = 0; i < data.length; i++) {
					                var place = data[i];
					                var placePosition = new kakao.maps.LatLng(place.y, place.x);

					                new kakao.maps.Marker({
					                    map: map,
					                    position: placePosition
					                });
					            }
					        }
					    }, {
					        location: coords,
					        radius: 1000
					    });
					}
				}
			}
		}).mount('.container')
	</script>
</body>
</html>