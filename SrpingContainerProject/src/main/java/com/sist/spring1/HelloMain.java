package com.sist.spring1;

public class HelloMain {
/*
 * 		HelloMain�� Hello�� �����Ѵ� => ���ռ��� ���� ���α׷�
 * 									-----------------
 * 									| ���������� ��ƴ�
 * 									| �����ÿ� �ٸ� Ŭ������ ������ ���� ���
 *  	�������̸� new�� ������� �ʴ´�
 *  			----- ���ռ��� ���� ���α׷�
 */
		public static void main(String[] args) {
			Hello hello=new Hello();
			String msg=hello.sayHello2("ȫ�浿");
			System.out.println(msg);
		}
}
