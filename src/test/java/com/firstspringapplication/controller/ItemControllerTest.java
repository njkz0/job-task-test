package com.firstspringapplication.controller;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Item;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.service.ItemService;
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
class ItemControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private ItemService itemService;

    @Test
    void save() throws URISyntaxException {
        Item testItem = new Item();

        when(itemService.save(any(Item.class))).thenReturn(testItem);

        RequestEntity<Item> requestEntity = new RequestEntity<>(testItem, HttpMethod.POST, new URI("/item"));
        ResponseEntity<Item> responseEntity = testRestTemplate.exchange(requestEntity, Item.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(itemService).save(any(Item.class));
    }

    @Test
    void saveWithBadRequest() throws URISyntaxException{
        Item testItem = new Item();

        when(itemService.save(any(Item.class))).thenThrow(new RuntimeException());

        RequestEntity<Item> requestEntity = new RequestEntity<>(testItem, HttpMethod.POST, new URI("/item"));
        ResponseEntity<Item> responseEntity = testRestTemplate.exchange(requestEntity, Item.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        verify(itemService).save(any(Item.class));

    }

    @Test
    void getOneItemById() throws URISyntaxException{
    }

    @Test
    void findAllItems() throws URISyntaxException{
    }

    @Test
    void updateItem() throws URISyntaxException{
    }

    @Test
    void delete() throws URISyntaxException{
    }
}