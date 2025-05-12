package com.example.letter.service;

import com.example.letter.model.News;
import com.example.letter.model.User;

import java.util.List;

public interface NewsLetterService {

    /**
     * Создание и отправка сообщения
     *
     * @param users     List {@link User}
     * @param messageAi {@link News}
     */
    void mailDoctorAi(List<User> users, News messageAi);
}
