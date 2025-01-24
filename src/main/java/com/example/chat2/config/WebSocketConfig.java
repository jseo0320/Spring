package com.example.chat2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 설정을 위한 Configuration 클래스
 * WebSocket은 클라이언트와 서버 간의 양방향 실시간 통신을 지원합니다.
 */
@Configuration 
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    /**
     * WebSocket 연결을 위한 엔드포인트를 등록하는 메소드
     * 클라이언트가 WebSocket 핸드셰이크 커넥션을 생성할 경로를 설정합니다.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")     // WebSocket 접속 엔드포인트를 /chat으로 설정
                .setAllowedOrigins("*")   // 모든 도메인에서의 접근을 허용 (CORS 설정)
                .withSockJS();            
    }

    /**
     * 메시지 브로커를 설정하는 메소드
     * 메시지의 발행(pub)과 구독(sub)에 대한 prefix를 설정합니다.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}