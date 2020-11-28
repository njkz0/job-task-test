package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.CartItemDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {


    private final CartItemDAO cartItemDAO;

    @Override
    public CartItem save(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()) == null) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant save CartItem");
    }

    @Override
    public CartItem update(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()) != null && cartItem.getId() != null) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant update CartItem");
    }

    @Override
    public CartItem findById(Integer id) {
        Optional<CartItem> cartItem = cartItemDAO.findById(id);
        if (cartItem != null) {
            return cartItem.get();
        } else return null;
    }

    @Override
    public List<CartItem> findAllCartItemsByCart(Cart cart) {
        return cartItemDAO.findAllByCart(cart);
    }

    @Override
    public void deleteById(Integer id) {
        cartItemDAO.deleteById(id);
    }

    @Override
    public Integer getCartPrice(Cart cart) {
        List <CartItem> cartItems = cartItemDAO.findAllByCart(cart);
        Integer result=0;
        for (CartItem cartItem: cartItems){
            result += cartItem.getItem().getPrice()*cartItem.getAmount();
        }
        return result;
    }
}
