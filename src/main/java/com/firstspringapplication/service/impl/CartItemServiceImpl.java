package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.CartItemDAO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {


    private final CartItemDAO cartItemDAO;

    @Override
    public CartItem save(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()).isEmpty()) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant save CartItem");
    }

    @Override
    public CartItem update(CartItem cartItem) {
        if (cartItemDAO.findById(cartItem.getId()).isPresent() && cartItem.getId() != null) {
            return cartItemDAO.save(cartItem);
        }
        throw new RuntimeException("Cant update CartItem");
    }

    @Override
    public CartItem findById(Integer id) {
        Optional<CartItem> cartItem = cartItemDAO.findById(id);
        return cartItem.orElseThrow(null);
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
    public Integer getCartPrice(Integer cartID) {
       return cartItemDAO.findSumOfCart(cartID);
       // var cartItems = cartItemDAO.findAllByCart(cart);
       // var result = 0;
       // for (CartItem cartItem : cartItems) result += cartItem.getItem().getPrice() * cartItem.getAmount();
       // return result;
    }

    @Override
    public Integer findTotalSum(Integer userID, Date dateFrom, Date dateTo) {
        Integer result = cartItemDAO.findTotalSum(userID, dateFrom, dateTo);
        if (result != null) {
            return result;
        }
        throw new RuntimeException("Items in that period not found");
    }
}
