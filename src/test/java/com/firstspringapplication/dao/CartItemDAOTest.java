package com.firstspringapplication.dao;

import com.firstspringapplication.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartItemDAOTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    CartItemDAO cartItemDAO;

    @Autowired
    ItemDAO itemDAO;


    List<CartItem> cartItems = new ArrayList<>();
    List <Date> dates = new ArrayList<>();
    User user;
    Cart cart;

    @BeforeEach
    void setUp() {
        User userTest = new User("test1", "test1", "test1", "test1", Profile.CLIENT);
        user = userDAO.save(userTest);

        Date dateTo = new Date();
        Date dateFrom= new Date(1l);
        dates.add(dateFrom);
        dates.add(dateTo);

        Cart cartTest1 = new Cart(user);
        cart = cartDAO.save(cartTest1);


        Item testItem1 = new Item("testItem1", 1000);
        Item savedItem1 = itemDAO.save(testItem1);

        Item testItem2 = new Item("testItem2", 2000);
        Item savedItem2 = itemDAO.save(testItem2);

        CartItem cartItemTest1 = new CartItem(savedItem1, cart, 1);
        CartItem cartItemTest2 = new CartItem(savedItem2, cart, 2);
        CartItem savedCartItem1 = cartItemDAO.save(cartItemTest1);
        CartItem savedCartItem2 = cartItemDAO.save(cartItemTest2);
        cartItems.add(savedCartItem1);
        cartItems.add(savedCartItem2);
    }


    @Test
    void findAllByCart() {
        List<CartItem> testList = cartItemDAO.findAllByCartId(cart.getId());
        assertEquals(2, testList.size());
        for (CartItem cartItem : testList) {
            assertEquals(cart.getTime(), cartItem.getCart().getTime());
        }
    }

    @Test
    void findTotalSum() {
       Integer result = cartItemDAO.findTotalSum(user.getId(), dates.get(0), dates.get(1));
       assertEquals(5000, result);
    }

    @Test
    void findSumOfCart() {
        Integer result = cartItemDAO.findSumOfCart(cart.getId());
        assertEquals(5000, result);
    }
}