package com.example.chat2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.chat2.model.ChatMessage;
import com.example.chat2.repository.ChatRepository;

/**
 * 채팅 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service  // 스프링의 서비스 계층 컴포넌트임을 나타냄
public class ChatService {
    private final ChatRepository chatRepository;  // 채팅 데이터 처리를 위한 리포지토리

    // 생성자 주입을 통한 ChatRepository 의존성 주입
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**
     * 모든 채팅 메시지를 조회하는 메서드
     * @return 저장된 모든 채팅 메시지 목록
     */
    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }

    /**
     * 새로운 채팅 메시지를 저장하는 메서드
     * @param message 저장할 채팅 메시지
     * @return 저장된 채팅 메시지
     */
    public ChatMessage saveMessage(ChatMessage message) {
        return chatRepository.save(message);
    }
}