package com.firstspringapplication.dao;

import com.firstspringapplication.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    List<Cart> carts = new ArrayList<>();
    List<CartItem> cartItems = new ArrayList<>();

    @BeforeEach
    void setUp() {
        User userTest = new User("test1", "test1", "test1", "test1", Profile.CLIENT);
        User savedUser = userDAO.save(userTest);

        Cart cartTest1 = new Cart(savedUser);
        Cart savedCart1 = cartDAO.save(cartTest1);
        carts.add(savedCart1);

        Item testItem1 = new Item("testItem1", 1000);
        Item savedItem1 = itemDAO.save(testItem1);

        Item testItem2 = new Item("testItem2", 2000);
        Item savedItem2 = itemDAO.save(testItem2);

        CartItem cartItemTest1 = new CartItem(savedItem1, savedCart1, 1);
        CartItem cartItemTest2 = new CartItem(savedItem2, savedCart1, 1);
        CartItem savedCartItem1 = cartItemDAO.save(cartItemTest1);
        CartItem savedCartItem2 = cartItemDAO.save(cartItemTest2);
        cartItems.add(savedCartItem1);
        cartItems.add(savedCartItem2);
    }

    @AfterEach
    void tearDown() {
        cartItems.stream().forEach(cartItem -> cartItemDAO.delete(cartItem));
        carts.stream().forEach(cart -> cartDAO.delete(cart));
    }

    @Test
    void findAllByCart() {
        List<CartItem> testList = cartItemDAO.findAllByCart(carts.get(0));
        assertEquals(2, testList.size());
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(carts.get(0).getTime(), testList.get(i).getCart().getTime());
        }
    }
}