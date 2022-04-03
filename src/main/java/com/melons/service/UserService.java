package com.melons.service;

import com.melons.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUser(User user);

    User loginUser(User user);

    void resigter(User user);
}
