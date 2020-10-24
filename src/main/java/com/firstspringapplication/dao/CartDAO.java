package com.firstspringapplication.dao;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDAO extends JpaRepository <Cart, Integer> {

   List<Cart> findAllByUser(User user);
}
