package com.firstspringapplication.controller;

import com.firstspringapplication.model.User;
import com.firstspringapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            User newUser = userService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getOneUserById(@PathVariable Integer id) {
        User foundUser = userService.findOne(id);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity <List<User>> findAllUsers(){
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            User changeUser = userService.update(user);
            return new ResponseEntity<>(changeUser, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<User> delete(@PathVariable Integer id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@RequestBody String login, String password) {
        try {
            User newUser = userService.findOneByLoginAndPassword(login, password);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
