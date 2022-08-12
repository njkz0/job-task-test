package com.firstspringapplication.service;
import com.firstspringapplication.model.Task;
import java.util.List;

public interface TaskService {
    Task save(Task task);
    Task update(Task Task);
    Task findById(Integer id);
    List<Task> findAllTasksByUserId(Integer cartId);
    void deleteById(Integer id);
}
