package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.CartDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {


    private final CartDAO cartDAO;

    @Override
    public Cart save(Cart cart) {
        List<Cart> carts = cartDAO.findAllByUserId(cart.getUser().getId());
        if (cart.getId() == null && carts.isEmpty()) {
            return cartDAO.save(cart);
        } else if (cart.getId() == null &&!carts.isEmpty()) {
            boolean userHaveNotOpenCart = true;
            for (var i : carts) {
                if (i.getStatus() == Status.OPEN) {
                    userHaveNotOpenCart = false;
                    break;
                }
            }
            if (userHaveNotOpenCart) return cartDAO.save(cart);
            else throw new RuntimeException("User already have open cart");

        }
        throw new RuntimeException("Cant save user");
    }

    @Override
    public Cart update(Cart cart) {
        if (cartDAO.findById(cart.getId()).isPresent() && cart.getId() != null) {
            return cartDAO.save(cart);
        }
        throw new RuntimeException("Cant update Cart");
    }

    @Override
    public Cart findById(Integer id) {
        Optional<Cart> cart = cartDAO.findById(id);
        return cart.orElse(null);
    }

    @Override
    public List<Cart> findAllCartsByUserId(Integer userId) {
        return cartDAO.findAllByUserId(userId);
    }

    @Override
    public void deleteById(Integer id) {
        cartDAO.deleteById(id);
    }

    @Override
    public List<Cart> getCartsBetweenDate(Integer userId, Date fromDate, Date toDate) {
        List<Cart> carts = cartDAO.findCartBetweenDates(userId, fromDate, toDate);
        if (!carts.isEmpty()) return carts;

        throw new RuntimeException("cant find carts between selected dates");
    }
}
