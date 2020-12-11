package com.firstspringapplication.service;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.User;

import java.util.Date;
import java.util.List;


public interface CartService {

     Cart save(Cart cart);
     Cart update(Cart cart);
     Cart findById(Integer id);
     List<Cart> findAllCartsByUserId(Integer userId);
     void deleteById(Integer id);
     List <Cart> getCartsBetweenDate(Integer id, Date fromDate, Date toDate);
}

