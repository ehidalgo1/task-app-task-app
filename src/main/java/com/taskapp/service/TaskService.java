package com.taskapp.service;

import com.taskapp.dto.TaskDto;
import com.taskapp.model.Task;
import com.taskapp.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TaskService {

    ResponseEntity findAll();

    ResponseEntity findByUuid(String uuid);

    ResponseEntity saveTask(Task task);

    ResponseEntity updateTask(String uuid, Task task);

    ResponseEntity assignTask(String uuidTask, String uuidUser);

    ResponseEntity deleteTask(String uuid);
}
