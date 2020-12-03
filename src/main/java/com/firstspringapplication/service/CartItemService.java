package com.firstspringapplication.service;

import com.firstspringapplication.dao.CartItemDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CartItemService {


    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
    CartItem findById(Integer id);
    List<CartItem> findAllCartItemsByCart(Cart cart);
    void deleteById(Integer id);
    Integer getCartPrice(Integer cartID);
    Integer findTotalSum(Integer userID, Date dateFrom, Date dateTo);
}
