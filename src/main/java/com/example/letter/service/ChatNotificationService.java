package com.example.letter.service;

import com.example.letter.model.response.ChatUserResponse;

public interface ChatNotificationService {

    /**
     * Создание чата
     *
     * @param chatUserResponse {@link ChatUserResponse}
     */
    void createChatNotification(ChatUserResponse chatUserResponse);
}
