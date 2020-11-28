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
        if (cartDAO.findById(cart.getId()) == null) {
            cart.setStatus(Status.OPEN);
            return cartDAO.save(cart);
        }
        throw new RuntimeException("Cant save Cart");
    }

    @Override
    public Cart update(Cart cart) {
        if (cartDAO.findById(cart.getId()) != null && cart.getId() != null) {
            return cartDAO.save(cart);
        }
        throw new RuntimeException("Cant update Cart");
    }

    @Override
    public Cart findById(Integer id) {
        Optional<Cart> cart = cartDAO.findById(id);
        if (cart != null) {
            return cart.get();
        } else return null;
    }

    @Override
    public List<Cart> findAllCartsByUser(User user) {
        return cartDAO.findAllByUser(user);
    }

    @Override
    public void deleteById(Integer id) {
        cartDAO.deleteById(id);
    }

    @Override
    public List<Cart> getCartsBetweenDate(Integer id, Date fromDate, Date toDate) {
        List<Cart> carts = cartDAO.findCartBetweenDates(id, fromDate, toDate);
        if(!carts.isEmpty()){
            return carts;
        } throw new RuntimeException("cant find carts between selected dates");
    }
}
