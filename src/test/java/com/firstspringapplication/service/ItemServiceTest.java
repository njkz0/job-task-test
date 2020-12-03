package com.firstspringapplication.service;

import com.firstspringapplication.dao.ItemDAO;
import com.firstspringapplication.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemDAO itemDAO;

    @Test
    void save() {
        Item expectedItem = new Item();

        Item testItem = new Item();
        testItem.setName("testName");

        when(itemDAO.findByName(anyString())).thenReturn(null);
        when(itemDAO.save(any(Item.class))).thenReturn(expectedItem);

        itemService.save(testItem);

        verify(itemDAO).findByName(anyString());
        verify(itemDAO).save(any(Item.class));


    }

    @Test
    void update() {
        Item updatedItem = new Item();
        updatedItem.setId(1);
        updatedItem.setName("test");

        Item resultItem = new Item();
        resultItem.setId(1);
        resultItem.setName("testResult");

        when(itemDAO.findById(anyInt())).thenReturn(Optional.of(updatedItem));
        when(itemDAO.save(any(Item.class))).thenReturn(resultItem);

        Item item = itemService.update(resultItem);

        assertEquals(resultItem.getName(), item.getName());

        verify(itemDAO).findById(anyInt());
    }

    @Test
    void findById() {
        Item foundItem = new Item();
        foundItem.setId(1);

        when(itemDAO.findById(anyInt())).thenReturn(Optional.of(foundItem));

        Item resultItem = itemService.findById(1);

        assertEquals(foundItem.getId(), resultItem.getId());
        verify(itemDAO).findById(anyInt());
    }

    @Test
    void findAllItems() {
        Item item1 = new Item();
        Item item2 = new Item();
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        when(itemDAO.findAll()).thenReturn(items);

        List <Item> resultItems = itemService.findAllItems();

        assertEquals(items.size(), resultItems.size());
        verify(itemDAO).findAll();
    }
}