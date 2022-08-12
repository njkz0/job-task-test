package com.firstspringapplication.service.impl;


import com.firstspringapplication.dao.TaskDAO;
import com.firstspringapplication.model.Task;
import com.firstspringapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            return taskDAO.save(task);
        }
        throw new RuntimeException("Cant save item: user already exists");
    }

    @Override
    public Task update(Task task) {
        if (taskDAO.findById(task.getId()).isPresent() && task.getId() != null) {
            return taskDAO.save(task);
        }
        throw new RuntimeException("Cant update item");
    }

    @Override
    public Task findById(Integer id) {
        Optional<Task> item = taskDAO.findById(id);
        return item.orElse(null);
    }

    @Override
    public List<Task> findAllTasksByUserId(Integer userId) {
        return taskDAO.findAllByCreatorId(userId);
    }

    @Override
    public void deleteById(Integer id) {
        taskDAO.deleteById(id);
    }
}
