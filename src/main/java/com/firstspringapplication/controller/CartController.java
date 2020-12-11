package com.firstspringapplication.controller;

import com.firstspringapplication.controller.request.ListCartRequestDTO;
import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<Cart> save(@RequestBody Cart cart) {
        try {
            Cart newCart = cartService.save(cart);
            return new ResponseEntity<>(newCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getOneCartById(@PathVariable Integer id) {
        Cart foundCart = cartService.findById(id);
        return new ResponseEntity<>(foundCart, HttpStatus.OK);
    }

    @GetMapping("/all_carts")
    public ResponseEntity<List<Cart>> findAllCartsByUser(@RequestBody User user) {
        return new ResponseEntity<>(cartService.findAllCartsByUserId(user.getId()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        try {
            Cart changeCart = cartService.update(cart);
            return new ResponseEntity<>(changeCart, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Integer id) {
        cartService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/find_between_dates")
    public ResponseEntity<List<Cart>> findBetweenDates(@RequestBody ListCartRequestDTO listCartRequestDTO) {
        try {
            List<Cart> cartList = cartService.getCartsBetweenDate(listCartRequestDTO.getId(), listCartRequestDTO.getDateFrom(), listCartRequestDTO.getDateTo());
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
