package com.firstspringapplication.dao;

import com.firstspringapplication.model.Task;
import com.firstspringapplication.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskDAOTest {

    @Autowired
    TaskDAO taskDAO;

    List<Task> tasks = new ArrayList<>();

    User creator = new User();
    @BeforeEach
    void setUp(){
        creator.setId(123);

        Task taskTest1 = new Task();
        taskTest1.setDescription("123");
        taskTest1.setCreator(creator);
        Task savedTask1 = taskDAO.save(taskTest1);

        Task taskTest2 = new Task();
        taskTest2.setDescription("456");
        taskTest2.setCreator(creator);
        Task savedTask2 = taskDAO.save(taskTest2);

        tasks.add(savedTask1);
        tasks.add(savedTask2);
    }

    @AfterEach
    void tearDown(){
        tasks.stream().forEach(user -> taskDAO.delete(user));
    }

    @Test
    void findAllByLogin() {
        List <Task> testTasks = taskDAO.findAllByCreatorId(creator.getId());
        assertEquals(tasks.size(), testTasks.size());
    }
}
