package com.firstspringapplication.service;

import com.firstspringapplication.dao.CartDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Item;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CartService {

     Cart save(Cart cart);
     Cart update(Cart cart);
     Cart findById(Integer id);
     List<Cart> findAllCartsByUser(User user);
     void deleteById(Integer id);
}

