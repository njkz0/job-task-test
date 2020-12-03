package com.firstspringapplication.controller;

import com.firstspringapplication.controller.request.CartItemTotalAmountDTO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart_item")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/save")
    public ResponseEntity<CartItem> save(@RequestBody CartItem cartItem) {
        try {
            CartItem newCartItem = cartItemService.save(cartItem);
            return new ResponseEntity<>(newCartItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getOneCartItemById(@PathVariable Integer id) {
        CartItem foundCartItem = cartItemService.findById(id);
        return new ResponseEntity<>(foundCartItem, HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CartItem>> findAllCartItemsByCart(@RequestBody Cart cart) {
        return new ResponseEntity(cartItemService.findAllCartItemsByCart(cart), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) {
        try {
            CartItem changeCartItem = cartItemService.update(cartItem);
            return new ResponseEntity<>(changeCartItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> delete(@PathVariable Integer id) {
        cartItemService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cart_amount")
    public ResponseEntity<CartItemTotalAmountDTO> getTotalAmountFromCart(Cart cart) {
        Integer result = cartItemService.getCartPrice(cart.getId());
        CartItemTotalAmountDTO resultDTO = new CartItemTotalAmountDTO();
        resultDTO.setTotalAmount(result);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping("/total_sum")
    public ResponseEntity<Integer> findTotalSum(Integer userID, Date dateFrom, Date dateTo) {
        try {
            Integer result = cartItemService.findTotalSum(userID, dateFrom, dateTo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
