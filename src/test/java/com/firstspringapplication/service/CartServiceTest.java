package com.firstspringapplication.service;

import com.firstspringapplication.dao.CartDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.User;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartDAO cartDAO;

    @Test
    void saveIfUserHaveClosedCarts() {
        User user = new User();
        user.setId(1);
        Cart expectedCart = new Cart(user);
        expectedCart.setStatus(Status.OPEN);

        Cart testCart = new Cart(user);
        testCart.setStatus(Status.CLOSED);

        Cart closedCart = new Cart(user);
        closedCart.setStatus(Status.CLOSED);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(closedCart);

        when(cartDAO.findAllByUserId(anyInt())).thenReturn(cartList);
        when(cartDAO.save(any(Cart.class))).thenReturn(expectedCart);

        Cart savedCart = cartService.save(testCart);

        assertEquals(expectedCart.getStatus(), savedCart.getStatus());
        verify(cartDAO).findAllByUserId(anyInt());
        verify(cartDAO).save(any(Cart.class));


    }

    @Test
    void saveIfUserHaveNoCarts(){
        User user = new User();
        user.setId(1);
        Cart expectedCart = new Cart(user);
        expectedCart.setStatus(Status.OPEN);

        Cart testCart = new Cart(user);

        List<Cart> cartList = new ArrayList<>();

        when(cartDAO.findAllByUserId(anyInt())).thenReturn(cartList);
        when(cartDAO.save(any(Cart.class))).thenReturn(expectedCart);

        Cart savedCart = cartService.save(testCart);

        assertEquals(expectedCart.getStatus(), savedCart.getStatus());
        verify(cartDAO).findAllByUserId(anyInt());
        verify(cartDAO).save(any(Cart.class));


    }
    @Test
    void update() {
        Cart expectedCart = new Cart();
        expectedCart.setId(1);

        Cart testCart = new Cart();
        testCart.setId(1);

        when(cartDAO.findById(anyInt())).thenReturn(Optional.of(expectedCart));
        when(cartDAO.save(any(Cart.class))).thenReturn(expectedCart);

        Cart savedCart = cartService.update(testCart);

        assertEquals(expectedCart.getId(), savedCart.getId());
        verify(cartDAO).findById(anyInt());
        verify(cartDAO).save(any(Cart.class));
    }

    @Test
    void findById() {
        Cart foundCart = new Cart();
        foundCart.setId(1);

        when(cartDAO.findById(anyInt())).thenReturn(Optional.of(foundCart));

        Cart resultCart = cartService.findById(1);

        assertEquals(foundCart.getId(), resultCart.getId());
        verify(cartDAO).findById(anyInt());
    }

    @Test
    void findAllCartsByUserId() {
        User user = new User();
        user.setId(1);

        Cart cart = new Cart(user);
        List <Cart> carts = new ArrayList<>();
        carts.add(cart);

        when(cartDAO.findAllByUserId(anyInt())).thenReturn(carts);

        List<Cart> resultCart = cartService.findAllCartsByUserId(1);

        assertEquals(resultCart.size(), carts.size());
        verify(cartDAO).findAllByUserId(anyInt());
    }

    @Test
    void getCartsBetweenDate() {
        Date fromDate = new Date(0);
        Date toDate = new Date();

        User user = new User();
        user.setId(1);

        Cart cart = new Cart(user);
        cart.setTime(new Date(toDate.getTime() - 5000));

        List <Cart> expectedCarts = new ArrayList<>();
        expectedCarts.add(cart);

        when(cartDAO.findCartBetweenDates(anyInt(), any(Date.class), any(Date.class))).thenReturn(expectedCarts);

        List <Cart> resultList = cartService.getCartsBetweenDate(1, fromDate, toDate);

        assertEquals(expectedCarts.size(), resultList.size());
        verify(cartDAO).findCartBetweenDates(anyInt(), any(Date.class), any(Date.class));

    }
}