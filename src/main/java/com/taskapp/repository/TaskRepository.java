package com.taskapp.repository;

import com.taskapp.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByUuid(String uuid);
}
