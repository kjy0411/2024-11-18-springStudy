package com.sist.web;

import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.*;
public class ChatHandler extends TextWebSocketHandler{

	// 1. 접속자 정보 저장
	private Map<String,WebSocketSession> users=new HashMap<String, WebSocketSession>();
	// 사용자가 접속한 경우
	// 접속자 정보 => WebSocketSession => IP/PORT => 브라우저에서 생성
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId()+"님이 입장하셨습니다");
		users.put(session.getId(), session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession ws:users.values()) {
			ws.sendMessage(message);
		}
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println(exception.getMessage());
		// 오류처리
	}
	// 접속 해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId()+"님이 종료하셨습니다");
		users.remove(session.getId());
	}
	// 2. 
}
