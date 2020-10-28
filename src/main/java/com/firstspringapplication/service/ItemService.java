package com.firstspringapplication.service;

import com.firstspringapplication.dao.ItemDAO;
import com.firstspringapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ItemService {

    public Item save(Item item);
    public Item update(Item item);
    public Item findById(Integer id);
    public List<Item> findAllItems();
    public void deleteById(Integer id);

}
