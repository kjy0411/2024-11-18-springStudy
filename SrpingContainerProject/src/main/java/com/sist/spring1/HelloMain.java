package com.sist.spring1;

public class HelloMain {
/*
 * 		HelloMain는 Hello에 의존한다 => 결합성이 강한 프로그램
 * 									-----------------
 * 									| 유지보수가 어렵다
 * 									| 에러시에 다른 클래스가 에러가 나는 경우
 *  	가급적이면 new를 사용하지 않는다
 *  			----- 결합성이 높은 프로그램
 */
		public static void main(String[] args) {
			Hello hello=new Hello();
			String msg=hello.sayHello2("홍길동");
			System.out.println(msg);
		}
}
