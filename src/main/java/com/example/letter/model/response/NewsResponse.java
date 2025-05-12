package com.example.letter.model.response;


import com.example.letter.model.News;
import com.example.letter.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class NewsResponse {
    private List<User> users;
    private News news;

}

