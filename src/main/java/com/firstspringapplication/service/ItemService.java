package com.firstspringapplication.service;

import com.firstspringapplication.dao.ItemDAO;
import com.firstspringapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    public Item save(Item item) {
        if (itemDAO.findById(item.getId()) == null) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Cant save item");
    }

    public Item update(Item item) {
        if (itemDAO.findById(item.getId()) != null && item.getId() != null) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Cant update item");
    }

    public Item findById(Integer id) {
        Optional<Item> item = itemDAO.findById(id);
        if (item != null) {
            return item.get();
        } else return null;
    }

    public List<Item> findAllItems(){
        return itemDAO.findAll();
    }

    public void deleteById(Integer id) {
        itemDAO.deleteById(id);
    }
}
