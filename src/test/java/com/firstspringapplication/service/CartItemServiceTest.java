package com.firstspringapplication.service;

import com.firstspringapplication.controller.request.ListCartRequestDTO;
import com.firstspringapplication.dao.CartItemDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemDAO cartItemDAO;

    Cart cart;
    Item item;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.setId(1);

        item = new Item();
        item.setId(1);
        ;
    }

    @AfterEach
    void tearDown() {
        cart = null;
        item = null;
    }

    @Test
    void save() {
        CartItem expectedCartItem = new CartItem();
        expectedCartItem.setAmount(3);

        CartItem savedCartItem = new CartItem();
        savedCartItem.setAmount(3);
        savedCartItem.setCart(cart);
        savedCartItem.setItem(item);


        when(cartItemDAO.findAllByCartId(anyInt())).thenReturn(new ArrayList<>());
        when(cartItemDAO.save(any(CartItem.class))).thenReturn(expectedCartItem);

        cartItemService.save(savedCartItem);

        assertEquals(expectedCartItem.getAmount(), savedCartItem.getAmount());

        verify(cartItemDAO).findAllByCartId(anyInt());
        verify(cartItemDAO).save(any(CartItem.class));
    }

    @Test
    void update() {
        CartItem changedCartItem = new CartItem();
        changedCartItem.setId(1);

        CartItem newCartItem = new CartItem();
        newCartItem.setId(1);
        newCartItem.setCart(cart);

        when(cartItemDAO.findById(anyInt())).thenReturn(Optional.of(changedCartItem));
        when(cartItemDAO.save(any(CartItem.class))).thenReturn(newCartItem);

        CartItem cartItem = cartItemService.update(newCartItem);

        assertNotNull(newCartItem.getCart());
        assertEquals(newCartItem.getCart().getId(), cartItem.getCart().getId());
        verify(cartItemDAO).findById(anyInt());
        verify(cartItemDAO).save(any(CartItem.class));
    }

    @Test
    void findById() {
        CartItem foundCartItem = new CartItem();
        foundCartItem.setId(1);

        when(cartItemDAO.findById(anyInt())).thenReturn(Optional.of(foundCartItem));

        CartItem result = cartItemService.findById(1);

        assertEquals(1, result.getId());
        verify(cartItemDAO).findById(anyInt());
    }

    @Test
    void findAllCartItemsByCartId() {
        CartItem foundCartItem = new CartItem();
        foundCartItem.setCart(cart);

        List<CartItem> foundList = new ArrayList<>();
        foundList.add(foundCartItem);

        when(cartItemDAO.findAllByCartId(anyInt())).thenReturn(foundList);

        List<CartItem> result = cartItemService.findAllCartItemsByCartId(1);

        assertEquals(foundList.size(), result.size());
        verify(cartItemDAO).findAllByCartId(anyInt());
    }

    @Test
    void getCartPrice() {

        when(cartItemDAO.findSumOfCart(anyInt())).thenReturn(3000);

        Integer result = cartItemService.getCartPrice(1);

        assertEquals(3000, result);
        verify(cartItemDAO).findSumOfCart(anyInt());
    }

    @Test
    void findTotalSum() {
        Date dateFrom = new Date(1);
        Date dateTo = new Date();

        when(cartItemDAO.findTotalSum(anyInt(), any(Date.class), any(Date.class))).thenReturn(3000);

        Integer result = cartItemService.findTotalSum(1, dateFrom, dateTo);

        assertEquals(3000, result);
        verify(cartItemDAO).findTotalSum(anyInt(), any(Date.class), any(Date.class));
    }
}