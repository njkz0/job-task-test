package com.firstspringapplication.controller;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.service.CartItemService;
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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartItemControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private CartItemService cartItemService;

    @Test
    void save() throws URISyntaxException {
        CartItem testCartItem = new CartItem();

        when(cartItemService.save(any(CartItem.class))).thenReturn(testCartItem);

        RequestEntity<CartItem> requestEntity = new RequestEntity<>(testCartItem, HttpMethod.POST, new URI("/cart_item/save"));
        ResponseEntity<CartItem> responseEntity = testRestTemplate.exchange(requestEntity, CartItem.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(cartItemService).save(any(CartItem.class));
    }

    @Test
    void saveWithBadRequest() throws URISyntaxException{
        CartItem testCartItem = new CartItem();

        when(cartItemService.save(any(CartItem.class))).thenThrow(new RuntimeException());

        RequestEntity<CartItem> requestEntity = new RequestEntity<>(testCartItem, HttpMethod.POST, new URI("/cart_item/save"));
        ResponseEntity<CartItem> responseEntity = testRestTemplate.exchange(requestEntity, CartItem.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        verify(cartItemService).save(any(CartItem.class));

    }

    @Test
    void getOneCartItemById() throws URISyntaxException{
    }

    @Test
    void findAllCartItemsByCart() throws URISyntaxException{
    }

    @Test
    void updateCartItem() throws URISyntaxException{
    }

    @Test
    void delete() throws URISyntaxException{
    }

    @Test
    void getTotalSumFromCart() throws URISyntaxException{
    }

    @Test
    void findTotalSum() throws URISyntaxException{
    }
}