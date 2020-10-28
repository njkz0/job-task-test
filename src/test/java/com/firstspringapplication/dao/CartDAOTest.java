package com.firstspringapplication.dao;

import com.firstspringapplication.model.Cart;
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
class CartDAOTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CartDAO cartDAO;

    List <User> users = new ArrayList<>();
    List <Cart> carts = new ArrayList<>();

    @BeforeEach
    void setUp(){
    User userTest = new User ("test1", "test1", "test1", "test1", Profile.CLIENT);
    User savedUser=userDAO.save(userTest);
    users.add(savedUser);

    Cart cartTest1 = new Cart (savedUser);
    Cart cartTest2 = new Cart (savedUser);
    Cart savedCart1 = cartDAO.save(cartTest1);
    Cart savedCart2 = cartDAO.save(cartTest2);
    carts.add(savedCart1);
    carts.add(savedCart2);
    }

    @AfterEach
    void tearDown(){
        carts.stream().forEach(cart -> cartDAO.delete(cart));
        users.stream().forEach(user -> userDAO.delete(user));
    }

    @Test
    void findAllByUser() {
        List <Cart> testCarts = cartDAO.findAllByUser(users.get(0));
        assertEquals(2, testCarts.size());
        assertEquals(carts.get(0).getUser().getFirstName(), testCarts.get(0).getUser().getFirstName());

        }
    }
