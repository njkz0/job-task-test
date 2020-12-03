package com.firstspringapplication.service;

import com.firstspringapplication.dao.UserDAO;
import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Test
    void save() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setLogin("test_login");
        expectedUser.setProfile(Profile.CLIENT);

        User testUser = new User();
        testUser.setLogin("test_login");

        when(userDAO.findAllByLogin(anyString())).thenReturn(new ArrayList<>());
        when(userDAO.save(any(User.class))).thenReturn(expectedUser);

        User user = userService.save(testUser);

        assertEquals(Profile.CLIENT, user.getProfile());

        verify(userDAO).findAllByLogin(anyString());
        verify(userDAO).save(any(User.class));
    }


    @Test
    void update() {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setFirstName("test");

        User resultUser = new User();
        resultUser.setId(1);
        resultUser.setFirstName("testResult");

        when(userDAO.findById(anyInt())).thenReturn(Optional.of(updatedUser));
        when(userDAO.save(any(User.class))).thenReturn(resultUser);

        User user = userService.update(resultUser);

        assertEquals(resultUser.getFirstName(), user.getFirstName());

        verify(userDAO).findById(anyInt());
    }

    @Test
    void findOne() {
        User foundUser = new User();
        foundUser.setId(1);

        when(userDAO.findById(anyInt())).thenReturn(Optional.of(foundUser));

        User resultUser = userService.findOne(1);

        assertEquals(foundUser.getId(), resultUser.getId());
        verify(userDAO).findById(anyInt());
    }

    @Test
    void findAll() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userDAO.findAll()).thenReturn(users);

        List <User> resultUsers = userService.findAll();

        assertEquals(users.size(), resultUsers.size());
        verify(userDAO).findAll();
    }

    @Test
    void findOneByLoginAndPassword() {
        User foundUser = new User();
        foundUser.setLogin("test1");
        foundUser.setPassword("test1");

        List <User> users = new ArrayList<>();
        users.add(foundUser);

        when(userDAO.findByLoginAndPassword(anyString(), anyString())).thenReturn(users);

        User resultUser = userService.findOneByLoginAndPassword("test1", "test1");

        assertEquals(foundUser.getLogin(), resultUser.getLogin());
        assertEquals(foundUser.getPassword(), resultUser.getPassword());
        verify(userDAO).findByLoginAndPassword(anyString(), anyString());
    }

   
}