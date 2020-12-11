package com.firstspringapplication.controller;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.CartService;
import com.firstspringapplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private CartService cartService;

    @Test
    void save() throws URISyntaxException {
        Cart testCart = new Cart();
        testCart.setTime(new Date());
        testCart.setStatus(Status.OPEN);

        when(cartService.save(any(Cart.class))).thenReturn(testCart);

        RequestEntity<Cart> requestEntity = new RequestEntity<>(testCart, HttpMethod.POST, new URI("/cart/save"));
        ResponseEntity<Cart> responseEntity = testRestTemplate.exchange(requestEntity, Cart.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(cartService).save(any(Cart.class));
    }

    @Test
    void saveWithBadRequest() throws URISyntaxException {
        Cart testCart = new Cart();
        when(cartService.save(any(Cart.class))).thenThrow(new RuntimeException());

        RequestEntity<Cart> requestEntity = new RequestEntity<>(testCart, HttpMethod.POST, new URI("/cart/save"));
        ResponseEntity<Cart> responseEntity = testRestTemplate.exchange(requestEntity, Cart.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        verify(cartService).save(any(Cart.class));
    }

    @Test
    void getOneCartById() throws URISyntaxException {
        Cart returnCart = new Cart();

        Cart testCart = new Cart();
        testCart.setId(1);
        when(cartService.findById(anyInt())).thenReturn(returnCart);

        RequestEntity<Integer> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI("/cart/1"));

        ResponseEntity<Cart> responseEntity = testRestTemplate.exchange(requestEntity, Cart.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cartService).findById(anyInt());
    }

    @Test
    void findAllCartsByUser() throws URISyntaxException {
        List<Cart> carts = new ArrayList<>();
        User user = new User();
        user.setId(1);

        when(cartService.findAllCartsByUserId(anyInt())).thenReturn(carts);

        RequestEntity <User> requestEntity = new RequestEntity<>(user, HttpMethod.GET, new URI("/cart/all_carts"));

        ResponseEntity<List> responseEntity = testRestTemplate.exchange(requestEntity, List.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cartService).findAllCartsByUserId(anyInt());
    }

    @Test
    void updateCart() throws URISyntaxException {
    }

    @Test
    void delete() throws URISyntaxException {
    }

    @Test
    void findBetweenDates() throws URISyntaxException {
    }
}