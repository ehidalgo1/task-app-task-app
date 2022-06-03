package com.taskapp.controller;

import com.taskapp.dto.TaskDto;
import com.taskapp.model.Task;
import com.taskapp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity getAllTask(){
        return taskService.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity getTaskByUuid(@PathVariable String uuid){
        logger.info("Invocando getTaskByUuid");
        return taskService.findByUuid(uuid);
    }

    @PostMapping("/")
    public ResponseEntity postTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity putTask(@PathVariable String uuid, @RequestBody Task task){
        return taskService.updateTask(uuid, task);
    }

    @PutMapping("/{uuidTask}/assign/user")
    public ResponseEntity assignTask(@PathVariable String uuidTask, @RequestParam String uuidUser){
        return taskService.assignTask(uuidTask, uuidUser);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteTask(@PathVariable String uuid){
        return taskService.deleteTask(uuid);
    }




}
