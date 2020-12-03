package com.firstspringapplication.dao;

import com.firstspringapplication.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDAO extends JpaRepository <Item, Integer> {

    Item findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM items WHERE price BETWEEN :fromPrice AND :toPrice")
    List<Item> findByPrice(Integer fromPrice, Integer toPrice);
}
