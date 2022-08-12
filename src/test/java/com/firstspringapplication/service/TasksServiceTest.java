package com.firstspringapplication.service;

import com.firstspringapplication.dao.TaskDAO;
import com.firstspringapplication.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TasksServiceTest {

    @MockBean
    private TaskDAO taskDAO;

    @Autowired
    private TaskService taskService;


    @Test
    void save() {
        Task expectedTask = new Task();
        expectedTask.setDescription("123");

        Task savedTask = new Task();
        savedTask.setDescription("456");
        savedTask.setDescription("123");


        when(taskDAO.save(any(Task.class))).thenReturn(expectedTask);

        taskService.save(savedTask);

        assertEquals(expectedTask.getDescription(), savedTask.getDescription());
        verify(taskDAO).save(any(Task.class));
    }

    @Test
    void update() {
        Task changedTask = new Task();
        changedTask.setId(1);

        Task newTask = new Task();
        newTask.setId(1);

        when(taskDAO.findById(anyInt())).thenReturn(Optional.of(changedTask));
        when(taskDAO.save(any(Task.class))).thenReturn(newTask);

        Task Task = taskService.update(newTask);

        assertNotNull(newTask.getId());
        assertEquals(newTask.getId(), changedTask.getId());
        verify(taskDAO).findById(anyInt());
        verify(taskDAO).save(any(Task.class));
    }

    @Test
    void findById() {
        Task foundTask = new Task();
        foundTask.setId(1);

        when(taskDAO.findById(anyInt())).thenReturn(Optional.of(foundTask));

        Task result = taskService.findById(1);

        assertEquals(1, result.getId());
        verify(taskDAO).findById(anyInt());
    }

    @Test
    void findAllTasksByUserId() {
        Task foundTask = new Task();
        foundTask.setDescription("123");

        List<Task> foundList = new ArrayList<>();
        foundList.add(foundTask);

        when(taskDAO.findAllByCreatorId(anyInt())).thenReturn(foundList);

        List<Task> result = taskService.findAllTasksByUserId(1);

        assertEquals(foundList.size(), result.size());
        verify(taskDAO).findAllByCreatorId(anyInt());
    }
}
