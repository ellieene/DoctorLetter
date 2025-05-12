package com.example.letter.service;

import javax.mail.Message;
import javax.mail.Session;

public interface MailSettingService {

    /**
     * Создание сообщения
     *
     * @param nameSubject Название заголовка
     * @param session     Сессия
     * @param toEmail     почта пользователя
     * @return {@link Message}
     */
    Message createMessage(String nameSubject, Session session, String toEmail);

    /**
     * Создание сессии
     *
     * @return {@link Session}
     */
    public Session setting();
}
