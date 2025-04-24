<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
	<div class="row justify-content-center" id="cartApp">
		<table class="table">
			<tr>
				<th class="text-center">번호</th>
				<th></th>
				<th class="text-center">상품명</th>
				<th class="text-center">가격</th>
				<th class="text-center">수량</th>
				<th class="text-center">등록일</th>
				<th class="text-center">비고</th>
			</tr>
			<c:if test="${list.size()==0 }">
				<tr>
					<td class="text-center" colspan="7">
						<h3>등록된 상품이 없습니다</h3>
					</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center">${vo.cno }</td>
					<td class="text-center">
						<img src="${vo.gvo.goods_poster }" style="width: 35px;height: 35px;">
					</td>
					<td><p style="max-width: 400px;">${vo.gvo.goods_name }</p></td>
					<td class="text-center">${vo.gvo.goods_price }</td>
					<td class="text-center">${vo.account }</td>
					<td class="text-center">${vo.dbday }</td>
					<td class="text-center">
						<input type="button" value="구매" class="btn-sm btn-success" @click="buy(${vo.cno },'${vo.gvo.goods_name }','${vo.gvo.goods_price }',${vo.account })">
						<a href="../mypage/cart_delete.do?cno=${vo.cno }" class="btn-sm btn-info">취소</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		var IMP = window.IMP; 
		IMP.init("imp42434332"); 
		let cartApp=Vue.createApp({
			data(){
				return {
					price:0,
					name:'',
					cno:0
				}
			},
			methods:{
				buy(cno,name,price,account){
					this.name=name;
					let temp=price.replace("원","")
					temp=temp.replace("-","")
					this.price=parseInt(temp)*account
					
					axios.get("../goods/buy_vue.do",{
						params:{
							cno:cno
						}
					}).then(res=>{
						console.log(res.data)
						if(res.data==='yes'){
							this.requestPay(this.name,this.price)
						}else{
							alert("구매실패!!")
						}
					}).catch(error=>{
						console.log(error.response)
					})
				},
				requestPay(name,price){
					console.log(name)
					console.log(price)
					IMP.request_pay({
				        pg: "kakaopay",
				        pay_method: "card",
				        merchant_uid: "ORD20180131-0000011",   // 주문번호
				        name: name,
				        amount: price,         // 숫자 타입
				        buyer_email: '',
				        buyer_name: '',
				        buyer_tel:'',
				        buyer_addr: '',
				        buyer_postcode: ''
				    }, function (rsp) {
				    	location.href="../mypage/buy_list.do"
				    });
				}
			}
		}).mount("#cartApp")
	</script>
</body>
</html>