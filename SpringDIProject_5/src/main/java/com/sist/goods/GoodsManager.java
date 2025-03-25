package com.sist.goods;

import org.springframework.stereotype.Component;

@Component("goods")
public class GoodsManager {
	public void display() {
		System.out.println("GoodsManager:display() Call..");
	}
}
