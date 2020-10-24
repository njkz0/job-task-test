package com.firstspringapplication.service;

import com.firstspringapplication.dao.CartItemDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    CartItemDAO cartItemDAO;

    public CartItem save(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()) == null) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant save CartItem");
    }

    public CartItem update(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()) != null && cartItem.getId() != null) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant update CartItem");
    }

    public CartItem findById(Integer id) {
        Optional<CartItem> cartItem = cartItemDAO.findById(id);
        if (cartItem != null) {
            return cartItem.get();
        } else return null;
    }

    public List<CartItem> findAllCartItemsByCart(Cart cart){
        return cartItemDAO.findAllByCart(cart);
    }

    public void deleteById(Integer id) {
        cartItemDAO.deleteById(id);
    }
}

