package com.firstspringapplication.controller;

import com.firstspringapplication.model.Task;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.TaskService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private TaskService taskService;

    @Test
    void saveNewTask() throws URISyntaxException {
       Task testTask = new Task();
        testTask.setDescription("test_description");
        User user = new User();
        user.setId(1);

        when(taskService.save(any(Task.class))).thenReturn(testTask);

        RequestEntity<Task> requestEntity = new RequestEntity<>(testTask, HttpMethod.POST, new URI("/save"));
        ResponseEntity<Task> responseEntity = testRestTemplate.exchange(requestEntity, Task.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(taskService).save(any(Task.class));
    }

    @Test
    void saveWithBadRequest() throws URISyntaxException{
        Task testTask = new Task();

        when(taskService.save(any(Task.class))).thenThrow(new RuntimeException());

        RequestEntity<Task> requestEntity = new RequestEntity<>(testTask, HttpMethod.POST, new URI("/save"));
        ResponseEntity<Task> responseEntity = testRestTemplate.exchange(requestEntity, Task.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        verify(taskService).save(any(Task.class));

    }

    @Test
    void getAllTasksByUserId() throws URISyntaxException {
        List<Task> tasks = new ArrayList<>();
        User user = new User();
        user.setId(1);

        when(taskService.findAllTasksByUserId(anyInt())).thenReturn(tasks);

        RequestEntity <User> requestEntity = new RequestEntity<>(user, HttpMethod.GET, new URI("/{id}/all"));

        ResponseEntity<List> responseEntity = testRestTemplate.exchange(requestEntity, List.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(taskService).findAllTasksByUserId(anyInt());
    }
}
