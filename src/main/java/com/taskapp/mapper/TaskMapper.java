package com.taskapp.mapper;

import com.taskapp.dto.TaskDto;
import com.taskapp.dto.UserDto;
import com.taskapp.model.Task;

public interface TaskMapper {

    TaskDto taskToTaskDto(Task task, UserDto userDto);

}
