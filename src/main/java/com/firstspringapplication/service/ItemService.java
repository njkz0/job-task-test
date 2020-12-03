package com.firstspringapplication.service;

import com.firstspringapplication.dao.ItemDAO;
import com.firstspringapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ItemService {

    Item save(Item item);
    Item update(Item item);
    Item findById(Integer id);
    List<Item> findAllItems();
    void deleteById(Integer id);


}
