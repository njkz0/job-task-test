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

@Service
public class CartService {

    @Autowired
    CartDAO cartDAO;

    public Cart save(Cart cart) {
        if (cartDAO.findById(cart.getId()) == null) {
            cart.setStatus(Status.OPEN);
            return cartDAO.save(cart);
        }
        throw new RuntimeException("Cant save Cart");
    }

    public Cart update(Cart cart) {
        if (cartDAO.findById(cart.getId()) != null && cart.getId() != null) {
            return cartDAO.save(cart);
        }
        throw new RuntimeException("Cant update Cart");
    }

    public Cart findById(Integer id) {
        Optional<Cart> cart = cartDAO.findById(id);
        if (cart != null) {
            return cart.get();
        } else return null;
    }

    public List<Cart> findAllCartsByUser(User user) {
        return cartDAO.findAllByUser(user);
    }

    public void deleteById(Integer id) {
        cartDAO.deleteById(id);
    }
}

