package com.firstspringapplication.service;

import com.firstspringapplication.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User findOne( Integer id);
    List<User> findAll();
    User findOneByLoginAndPassword(String login, String password);
    void delete(Integer id);
}
