package com.firstspringapplication.controller;

import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private UserService userService;

    @Test
    void save() throws URISyntaxException {
        User testUser = new User();
        testUser.setLogin("test_login");
        testUser.setPassword("test_pass");
        testUser.setProfile(Profile.CLIENT);

        when(userService.save(any(User.class))).thenReturn(testUser);

        RequestEntity<User> requestEntity = new RequestEntity<>(testUser, HttpMethod.POST, new URI("/user"));
        ResponseEntity<User> responseEntity = testRestTemplate.exchange(requestEntity, User.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(userService).save(any(User.class));
    }

    @Test
    void saveWithBadRequest() throws URISyntaxException {
        when(userService.save(any(User.class))).thenThrow(new RuntimeException());

        RequestEntity<User> requestEntity = new RequestEntity<>(
                User.builder()
                        .login("test_login")
                        .password("test_pass")
                        .profile(Profile.CLIENT)
                        .build(),
                HttpMethod.POST, new URI("/user"));
        ResponseEntity<User> responseEntity = testRestTemplate.exchange(requestEntity, User.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        verify(userService).save(any(User.class));
    }

    @Test
    void getOneUserById() throws  URISyntaxException{
        User returnUser = new User();
        returnUser.setId(1);
        returnUser.setLogin("testLogin");
        returnUser.setLogin("testPassword");

        User testUser = new User ();
        testUser.setId(1);
        when(userService.findOne(anyInt())).thenReturn(returnUser);

        RequestEntity<Integer> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI("/user/find/1"));

        ResponseEntity <User> responseEntity = testRestTemplate.exchange(requestEntity, User.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(userService).findOne(anyInt());
    }

    @Test
    void findAllUsers() throws  URISyntaxException{
        List<User> userList = new ArrayList<>();
        when(userService.findAll()).thenReturn(userList);

        RequestEntity  requestEntity = new RequestEntity(HttpMethod.GET, new URI("/user/all"));

        ResponseEntity responseEntity = testRestTemplate.exchange(requestEntity, List.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(userService).findAll();
    }

    @Test
    void updateUser() throws  URISyntaxException{
        User testUser = new User();
        testUser.setId(1);


        when(userService.update(any(User.class))).thenReturn(testUser);

        RequestEntity <User> requestEntity = new RequestEntity<>(testUser, HttpMethod.PUT, new URI("/user"));

        ResponseEntity <User> responseEntity = testRestTemplate.exchange(requestEntity, User.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(userService).update(any(User.class));
    }

    @Test
    void updateUserWithBadRequest() throws  URISyntaxException{
       User testUser = new User();

        when(userService.update(any(User.class))).thenThrow(new RuntimeException(""));

       RequestEntity <User> requestEntity = new RequestEntity<>(testUser, HttpMethod.PUT, new URI("/user"));

       ResponseEntity <User> responseEntity = testRestTemplate.exchange(requestEntity, User.class);

       assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
       verify(userService).update(any(User.class));

    }


    @Test
    void delete() throws  URISyntaxException{

        doNothing().when(userService).delete(anyInt());

        RequestEntity <Integer> requestEntity = new RequestEntity<>(HttpMethod.DELETE, new URI("/user/1"));
        ResponseEntity <Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(userService).delete(anyInt());


    }
}