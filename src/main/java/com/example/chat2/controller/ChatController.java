package com.example.chat2.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat2.model.ChatMessage;
import com.example.chat2.service.ChatService;

/**
 * 채팅 기능을 위한 REST API 컨트롤러
 * WebSocket과 REST API를 모두 사용하여 채팅 기능을 구현합니다.
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;  // 채팅 관련 비즈니스 로직을 처리하는 서비스

    // 생성자 주입을 통한 ChatService 의존성 주입
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * WebSocket을 통해 클라이언트로부터 메시지를 받아 처리하는 메소드
     * @MessageMapping("/sendMessage"): 클라이언트가 /app/sendMessage로 메시지를 보낼 때 호출됨
     * @SendTo("/topic/messages"): 처리된 메시지를 구독자들에게 브로드캐스트
     */
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(ChatMessage message) {
        return chatService.saveMessage(message);  // 메시지를 저장하고 반환
    }

    /**
     * 이전 채팅 메시지 목록을 조회하는 REST API 엔드포인트
     * HTTP GET 요청으로 /api/chat/messages 경로에 접근하면 호출됨
     */
    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        return chatService.getAllMessages();  // 저장된 모든 메시지를 반환
    }
}
