package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*  
 *  스프링 메모리 할당
 *  => 패키지 단위
 *  <context:component-scan base-package="com.sist.*"/>
 *  	=> 선택적으로 메모리 할당
 *  	=> 어노테이션
 *   => @Component	: 일반 객체 ~Manager
 *  	@Repository : 저장소 ~DAO
 *  	@Service	: 의존성을 낮게/DAO여러개를 묶어서 ~Service
 *  	@Controller	: 화면 변경 Model
 *  	@RestController	: 자바스크립트 / 코틀린 / 장고 Model => RestFul
 *  	@ControllerAdvice	: 공통 예외처리 => Controller 
 *  	@RestControllerAdvice	: 공통 예외처리 => RestController 
 *  
 *  	@Baen
 */
// model <==> service <===> dao <==> 오라클 연동
@Repository
public class BusanInfoDAO {
	// 스프링에서 메모리 할당을 한 클래스의 주소를 받는 경우 => 클래스 객체 주소
	@Autowired
	private BusanInfoMapper mapper;
	// 구현
	/*  
	 *  MyBatis : XML , Annotation
	 *  		=> XML + Annotation
	 *  				 ========== 간단한 SQL문장
	 *  		   === 동적 쿼리 / SQL문장이 긴 경우
	 *  		=> <select> @Select()
	 *  		=> id resultType / parameterType
	 *  			|	|			|
	 *  		메소드명	리턴형		매개변수
	 *  				====
	 *  				VO / List
	 *  				|	|
	 *  				|	selectList
	 *  				selectOne
	 *  		=> getConnection / disConnection 처리
	 *  => ORM => 관계형 데이터베이스에 대한 라이브러리 (데이터베이스)
	 *  	| MyBatis (IBatis)
	 *  	| JPA (Hibernate) => DataSet
	 */
	public List<BusanInfoVO> BusanInfoListData(Map map){
		return mapper.BusanInfoListData(map);
	}
	public int BusanInfoTotalPage(int cno) {
		return mapper.BusanInfoTotalPage(cno);
	}
	public BusanInfoVO BusanInfoDetailData(int no) {
		return mapper.BusanInfoDetailData(no);
	}
}
