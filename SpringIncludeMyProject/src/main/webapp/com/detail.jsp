<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript">
let check=false
$(function(){
	$('.btn-map').click(function(){
		if(check){
			$('.div-map').hide()
			check=false
		}else{
			$('.div-map').show()
			check=true
		}
	})
})
</script>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center">
				<img src="${cvo.poster!=null?'https://pds.saramin.co.kr/company'+=cvo.poster:'../com/logo/noimg.png' }"
				title="${cvo.name }" style="width: 600px; height: 200px;border: 1px solid black;" class="img-rounded">
			</td>
		</tr>
		<tr>
			<td class="text-center">
				<h3>${cvo.name }</h3>
			</td>
		</tr>
	</table>
	<table class="table">
		<tr>
		<c:if test="${cvo.dbestdate!=null }">
			<th class="text-center">
				<span><i class="bi-clock-history text-primary me-2" style="font-size: 50px"></i></span><br>
				<span>설립일</span>
			</th>
		</c:if>
		<c:if test="${cvo.c_type!=null }">
			<th class="text-center">
				<span><i class="bi-buildings text-primary me-2" style="font-size: 50px"></i></span><br>
				<span>기업형태</span>
			</th>
		</c:if>
		<c:if test="${cvo.ecount!=0 }">
			<th class="text-center">
				<span><i class="bi-currency-exchange text-primary me-2" style="font-size: 50px"></i></span><br>
				<span>사원수</span>
			</th>
		</c:if>
		<c:if test="${cvo.jo!=0 or cvo.uk!=0 or cvo.man!=0}">
			<th class="text-center">
				<span><i class="bi-coin text-primary me-2" style="font-size: 50px"></i></span><br>
				<span>매출</span>
			</th>
		</c:if>
		</tr>
		<tr>
		<c:if test="${cvo.dbestdate!=null }">
			<td class="text-center">
				<span>${cvo.dbestdate }</span>
			</td>
		</c:if>
		<c:if test="${cvo.c_type!=null }">
			<td class="text-center">
				<span>${cvo.c_type }</span>
			</td>
		</c:if>
		<c:if test="${cvo.ecount!=0 }">
			<td class="text-center">
				<span>${cvo.ecount }</span>
			</td>
		</c:if>
		<c:if test="${cvo.jo!=0 or cvo.uk!=0 or cvo.man!=0}">
			<td class="text-center">
				<span>
					${(cvo.jo!=0?cvo.jo+='조':'')+=(cvo.uk!=0?cvo.uk+='억':'')+=(cvo.man!=0?cvo.man+='만':'') }
				</span>
			</td>
		</c:if>
		</tr>
	</table>
	<table>
	</table>
	<div>
		<c:if test="${cvo.industry!=null }">
			<div class="col-sm-6 mt-3">
				<div class="col-sm-3">업종</div>
				<div class="col-sm-9">${cvo.industry }</div>
			</div>
		</c:if>
		<c:if test="${cvo.representative!=null }">
			<div class="col-sm-6 mt-3">
				<div class="col-sm-3">대표자명</div>
				<div class="col-sm-9">${cvo.representative }</div>
			</div>
		</c:if>
		<c:if test="${cvo.homepage!=null }">
			<div class="col-sm-6 mt-3">
				<div class="col-sm-3">홈페이지</div>
				<div class="col-sm-9">
					<a href="${cvo.homepage }" target="_blank">${cvo.homepage }</a>
				</div>
			</div>
		</c:if>
		<c:if test="${cvo.bu_details!=null }">
			<div class="col-sm-6 mt-3">
				<div class="col-sm-3">사업내용</div>
				<div class="col-sm-9">${cvo.bu_details }</div>
			</div>
		</c:if>
		<c:if test="${cvo.address!=null }">
			<div class="col-sm-6 mt-3">
				<div class="col-sm-3">주소</div>
				<div class="col-sm-9">
					${cvo.address }<br>
					<a href="javascript:map()" class="btn btn-sm btn-outline-primary btn-map" style="border: 1px solid; border-radius: 2px; cursor: pointer;">
						<i class="bi-map-fill"></i>지도
					</a>
					<a href="#" target="_blank" class="btn btn-sm btn-outline-primary btn-find-map" style="border: 1px solid; border-radius: 2px; cursor: pointer;">
						<i class="bi-binoculars"></i>길찾기
					</a>
				</div>
			</div>
			<div class="map mt-2">
				<div class="content div-map">
					<div id="map" style="width: 100%; height: 350px; border-radius: 10px">
					</div>
					<p>
						<em class="link">
							<a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
								혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
							</a>
						</em>
					</p>
				</div>
			</div>
		</c:if>
	</div>
	<div class="col-sm-12">
		<c:if test="${cvo.introduction != null }">
			<hr>
			<h4 class="mb-3">기업 소개</h4>
			<pre style="white-space: pre-line;">${cvo.introduction }</pre>
		</c:if>
	</div>
	<div class="col-sm-12">
		<a class="btn btn-sm btn-primary" href="javascript:history.back()" style="float: right;">목록</a>
	</div>
	<c:if test="${cvo.address!=null }">
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f5c499003a5ed7ef16b41579b19a32a3&libraries=services"></script>
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(33.450701,126.570667), // 지도의 중심좌표
				level : 3// 지도의 확대 레벨
			};
	
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption);
	
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${cvo.address}',function(result, status) {
				// 정상적으로 검색이 완료됐으면 
				if (status === kakao.maps.services.Status.OK) {
	
					var coords = new kakao.maps.LatLng(result[0].y,result[0].x);
	
					// 결과값으로 받은 위치를 마커로 표시합니다
					var marker = new kakao.maps.Marker({map : map,position : coords});
	
					// 인포윈도우로 장소에 대한 설명을 표시합니다
					var infowindow = new kakao.maps.InfoWindow({content : '<div style="width:150px;text-align:center;padding:6px 0;">${cvo.name}</div>'});
					infowindow.open(map, marker);
	
					// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
	
					$('.btn-find-map').attr("href","https://map.kakao.com/link/to/${cvo.name},"+ result[0].y+ ","+ result[0].x)
	
					$('.div-map').hide();
				}
			});
		</script>
	</c:if>
</body>
</html>