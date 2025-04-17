<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
	<div class="container" id="app">
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-center" v-for="img in images">
						<img :src="'https://www.menupan.com'+img" style="width: 100px">
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td rowspan="8" width="30%">
						<img :src="'https://www.menupan.com'+vo.poster" style="width: 240px;" v-if="vo.poster">
					</td>
					<td colspan="2" width="70%">
						<h3><span id="name">{{vo.name}}</span>(<span style="color: orange;">{{vo.score}}</span>)</h3>
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">주소</td>
					<td width="50%">
						{{vo.address}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">전화</td>
					<td width="50%">
						{{vo.phone}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">음식종류</td>
					<td width="50%">
						{{vo.type}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">가격</td>
					<td width="50%">
						{{vo.price}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">영업시간</td>
					<td width="50%">
						{{vo.time}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">주차</td>
					<td width="50%">
						{{vo.parking}}
					</td>
				</tr>
				<tr>
					<td width="20%" class="text-center">테마</td>
					<td width="50%">
						{{vo.theme}}
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<td>
						<pre>{{vo.content}}</pre>
					</td>
				</tr>
				<tr>
					<td>
						<div id="map" style="width:100%;height:350px;"></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="module">
		import {createApp} from "https://unpkg.com/vue@3/dist/vue.esm-browser.js"
		import axiosClient from "../js/api.js"
		
		createApp({
			data(){
				return {
					fno:${param.fno},
					vo:{},
					images:[]
				}
			},
			mounted(){
				this.dataRecv()
			},
			methods:{
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
					var name=this.vo.name;
					// 주소로 좌표를 검색합니다
					geocoder.addressSearch(this.vo.address, function(result, status) {
	
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
				},
				async dataRecv(){
					const res=await axiosClient.get('food/detail_vue.do',{
						params:{
							fno:this.fno
						}
					})
					console.log(res)
					this.vo=res.data
					this.images=res.data.images.split(",")
					this.images.pop()
					if(window.kakao && window.kakao.maps){
						this.initMap()
					}else{
						this.addScript()
					}
				}
			}
		}).mount("#app")
	</script>
</body>
</html>