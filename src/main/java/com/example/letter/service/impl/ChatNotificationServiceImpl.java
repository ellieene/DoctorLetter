package com.example.letter.service.impl;

import com.example.letter.model.response.ChatUserResponse;
import com.example.letter.service.ChatNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.logging.Logger;

/**
 * Создание и отправка сообщения с чатом
 */
@Service
@RequiredArgsConstructor
public class ChatNotificationServiceImpl implements ChatNotificationService {

    private final Logger logger = Logger.getLogger(ChatNotificationServiceImpl.class.getName());
    private final MailSettingServiceImpl mailSettingServiceImpl;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void createChatNotification(ChatUserResponse chatUserResponse) {
        Session session = mailSettingServiceImpl.setting();

        try {

            Message message = mailSettingServiceImpl
                    .createMessage("Запись", session, chatUserResponse.getUser().getEmail());
            message.setContent(htmlContent(
                    chatUserResponse.getDoctor(),
                    chatUserResponse.getUser().getLogin()
            ), "text/html; charset=UTF-8");

            Transport.send(message);
        } catch (Exception e) {
            logger.severe("Ошибка при отправке письма: " + e.getMessage());
        }
    }

    /**
     * Создание сообщение в HTML
     *
     * @param doctor Название доктора
     * @param login  Логин пользователя
     * @return html
     */
    private String htmlContent(String doctor, String login) {
        Context context = new Context();
        context.setVariable("doctor", doctor);
        context.setVariable("login", login);
        return templateEngine.process("CreateChat", context); // Указываем только имя шаблона
    }
}
