package com.firstspringapplication.dao;

import com.firstspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository <User, Integer> {

    List<User> findAllByLogin(String login);
    List<User> findByLoginAndPassword(String login, String password);
}
