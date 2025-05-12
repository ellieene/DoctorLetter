package com.example.letter.consumer;

import com.example.letter.model.response.ChatUserResponse;
import com.example.letter.model.response.NewsResponse;
import com.example.letter.service.impl.ChatNotificationServiceImpl;
import com.example.letter.service.impl.NewsLetterServiceImpl;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NewsLetterServiceImpl newsLetterServiceImpl;
    private final ChatNotificationServiceImpl chatNotificationServiceImpl;

    /**
     * Получение сообщение для новостей
     *
     * @param message Сообщение из Kafka
     */
    @KafkaListener(topics = "letter", groupId = "group-service")
    public void listenNews(String message) {
        Gson gson = new Gson();
        NewsResponse response = gson.fromJson(message, NewsResponse.class);
        newsLetterServiceImpl.mailDoctorAi(response.getUsers(), response.getNews());
    }

    /**
     * Получение сообщения о создание чата
     *
     * @param message Сообщение Kafka
     */
    @KafkaListener(topics = "chat-notification", groupId = "group-service")
    public void listenChat(String message) {
        Gson gson = new Gson();
        ChatUserResponse response = gson.fromJson(message, ChatUserResponse.class);
        chatNotificationServiceImpl.createChatNotification(response);
    }

}
