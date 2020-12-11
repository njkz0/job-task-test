package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.CartItemDAO;
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
        List<CartItem> cartItems = cartItemDAO.findAllByCartId(cartItem.getCart().getId());
        for (var i : cartItems) {
            if (cartItem.getItem().getId().equals(i.getItem().getId())) {
                throw new RuntimeException("CartItem in that cart is already exsist");
            }
        }
        return cartItemDAO.save(cartItem);
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
    public List<CartItem> findAllCartItemsByCartId(Integer cartId) {
        return cartItemDAO.findAllByCartId(cartId);
    }

    @Override
    public void deleteById(Integer id) {
        cartItemDAO.deleteById(id);
    }

    @Override
    public Integer getCartPrice(Integer cartID) {
        Integer result = cartItemDAO.findSumOfCart(cartID);
        if (result != null) return result;
        throw new RuntimeException("Items in that cart not found");
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
