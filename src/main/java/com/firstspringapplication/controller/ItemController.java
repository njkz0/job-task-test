package com.firstspringapplication.controller;

import com.firstspringapplication.model.Item;
import com.firstspringapplication.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item) {
        try {
            Item newItem = itemService.save(item);
            return new ResponseEntity<>(newItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Item> getOneItemById(@PathVariable Integer id) {
        Item foundItem = itemService.findById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Item> findAllItems(){
        return new ResponseEntity(itemService.findAllItems(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        try {
            Item changeItem = itemService.update(item);
            return new ResponseEntity<Item>(changeItem, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Item> delete(@PathVariable Integer id){
        itemService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
