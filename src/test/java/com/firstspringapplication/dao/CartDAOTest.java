package com.firstspringapplication.dao;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ActiveProfiles(profiles = {"test"})
class CartDAOTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CartDAO cartDAO;

    List <User> users = new ArrayList<>();
    List <Cart> carts = new ArrayList<>();
    List <Date> dates = new ArrayList<>();

    @BeforeEach
    void setUp(){
    User userTest = new User ("test1", "test1", "test1", "test1", Profile.CLIENT);
    User savedUser=userDAO.save(userTest);
    users.add(savedUser);

    Date now = new Date();
    Long milliesNow = now.getTime();
    Date dateFrom = new Date(milliesNow - 1l);
    Date oneDayAgo = new Date(milliesNow - 86400000l);
    Date twoDayAgo = new Date(milliesNow - 172800000l);
    dates.add(dateFrom);
    dates.add(oneDayAgo);
    dates.add(twoDayAgo);

    Cart cartTest1 = new Cart (savedUser);
    cartTest1.setTime(now);
    Cart cartTest2 = new Cart (savedUser);
    cartTest2.setTime(oneDayAgo);
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

    @Test
    void findCartBetweenDates() {
        List <Cart> result = cartDAO.findCartBetweenDates(users.get(0).getId(), dates.get(2), dates.get(0));
        assertEquals(1, result.size());
    }
}
