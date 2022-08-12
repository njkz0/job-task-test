package com.firstspringapplication.threads;

import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.Task;
import com.firstspringapplication.service.TaskService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RenderThread implements Runnable{

    private Task task;
    private final TaskService taskService;

    @Override
    public void run() {
        try {
            if(task.getId() != null && task.getStatus() == Status.RENDERING) {
                Thread.sleep((long) (/*Math.random() * */60000));
                task.setStatus(Status.COMPLETE);
                taskService.update(task);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
