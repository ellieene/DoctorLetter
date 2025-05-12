package com.example.letter.model.response;

import com.example.letter.model.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatUserResponse {

    private User user;
    private String doctor;
}
