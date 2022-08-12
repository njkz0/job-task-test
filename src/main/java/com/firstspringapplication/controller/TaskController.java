package com.firstspringapplication.controller;

import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.Task;
import com.firstspringapplication.service.TaskService;
import com.firstspringapplication.threads.RenderThread;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<Task> saveNewTask(@RequestBody Task task) {
        try {
            task.setStatus(Status.RENDERING);
            task.setCreateDate(new Date());
            Task newTask = taskService.save(task);
            if (newTask.getCreator().getId() == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            Thread thread = new Thread(new RenderThread(newTask, taskService));
            thread.start();
            return new ResponseEntity<>(newTask, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/close")
    public ResponseEntity<Task> saveTaskClosed(@RequestBody Integer taskId) {
        try {
            Task task = taskService.findById(taskId);
            if (task != null) {
                task.setStatus(Status.CLOSED);
                Task newTask = taskService.update(task);
                return new ResponseEntity<>(newTask, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(taskService.findAllTasksByUserId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
