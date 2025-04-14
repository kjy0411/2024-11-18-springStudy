package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sist.web.ChatHandler;

// 메모리 할당
@Configuration
// WebSocket활성화
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer{

	// => 사용자가 보내는 사이트를 등록
	// => ws:// => chat-ws.do
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatHandler(),"/chat-ws.do");
	}
	@Bean
	public ChatHandler chatHandler() {
		return new ChatHandler();
	}
}
