package com.example.letter.service.impl;

import com.example.letter.model.News;
import com.example.letter.model.User;
import com.example.letter.service.NewsLetterService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.List;
import java.util.logging.Logger;


/**
 * Рассылка Новосте по почте
 */
@Service
@RequiredArgsConstructor
public class NewsLetterServiceImpl implements NewsLetterService {

    private final Logger logger = Logger.getLogger(NewsLetterServiceImpl.class.getName());
    private final MailSettingServiceImpl mailSettingServiceImpl;
    private final SpringTemplateEngine templateEngine;


    @SneakyThrows
    @Override
    public void mailDoctorAi(List<User> users, News messageAi) {
        Session session = mailSettingServiceImpl.setting();
        for (User user : users) {
            try {
                Message message = mailSettingServiceImpl
                        .createMessage("Новое открытие!", session, user.getEmail());
                message.setContent(htmlContent(messageAi, user.getLogin()), "text/html; charset=UTF-8");
                Transport.send(message);
                logger.info("Письмо успешно отправлено пользователю: " + user.getEmail());
            } catch (Exception e) {
                logger.severe("Ошибка при отправке письма пользователю " + user.getEmail() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Передача полей в html файл
     *
     * @param news  {@link News}
     * @param login логин пользователя
     * @return html
     */
    private String htmlContent(News news, String login) {
        Context context = new Context();
        context.setVariable("news", news);
        context.setVariable("login", login);
        return templateEngine.process("../templates/news", context);
    }
}
