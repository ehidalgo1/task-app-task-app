package com.taskapp.controller;

import com.taskapp.dto.TaskDto;
import com.taskapp.model.Task;
import com.taskapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getAllTask(){
        return taskService.findAll();
    }

    @GetMapping("/{uuid}")
    public Task getTaskByUuid(@PathVariable String uuid){
        return taskService.findByUuid(uuid);
    }

    @GetMapping


    @PostMapping("/")
    public Task postTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @PutMapping("/{uuid}")
    public Task putTask(@PathVariable String uuid, @RequestBody Task task){
        return taskService.updateTask(uuid, task);
    }

    @PutMapping("/{uuidTask}/assign/user")
    public Task assignTask(@PathVariable String uuidTask, @RequestParam String uuidUser){
        return taskService.assignTask(uuidTask, uuidUser);
    }




}
