package com.taskapp.service;

import com.taskapp.dto.TaskDto;
import com.taskapp.model.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findByUuid(String uuid);

    Task saveTask(Task task);

    Task updateTask(String uuid, Task task);

    Task assignTask(String uuidTask, String uuidUser);
}
