package com.example.letter.service.impl;

import com.example.letter.service.MailSettingService;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Настройка почты
 */
@Getter
@Service
public class MailSettingServiceImpl implements MailSettingService {


    @Value("${doctor-ai-mail}")
    private String username;

    @Value("${doctor-ai.password}")
    private String password;


    @Override
    public Session setting() {
        return Session.getInstance(settingsMail(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @SneakyThrows
    @Override
    public Message createMessage(String nameSubject, Session session, String toEmail) {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(nameSubject);
        return message;
    }

    /**
     * Настройка почты
     *
     * @return {@link Properties}
     */
    private Properties settingsMail() {
        String host = "smtp.mail.ru";
        String port = "465";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        return props;

    }
}
