package com.firstspringapplication.dao;

import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    List <User> users = new ArrayList<>();

    @BeforeEach
    void setUp(){
        User userTest1 = new User("testlogin", "testpassword", "test1", "test1", Profile.CLIENT);
        User savedUser1 = userDAO.save(userTest1);
        User userTest2 = new User("testlogin", "testpassword2", "test1", "test2", Profile.CLIENT);
        User savedUser2 = userDAO.save(userTest2);
        users.add(savedUser1);
        users.add(savedUser2);
    }

    @AfterEach
    void tearDown(){
        users.stream().forEach(user -> userDAO.delete(user));
    }

    @Test
    void findAllByLogin() {
        List <User> testUsers = userDAO.findAllByLogin("testlogin");
        assertEquals(users.size(), testUsers.size());
    }

    @Test
    void findByLoginAndPassword() {
        User testUser = userDAO.findByLoginAndPassword("testlogin", "testpassword");
        assertNotNull(testUser);

    }
}