package com.taskapp.mapper.impl;

import com.taskapp.dto.TaskDto;
import com.taskapp.dto.UserDto;
import com.taskapp.mapper.TaskMapper;
import com.taskapp.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    Logger logger = LoggerFactory.getLogger(TaskMapperImpl.class);

    @Override
    public TaskDto taskToTaskDto(Task task, UserDto userDto) {
        TaskDto taskDto = new TaskDto();
        if (task==null ){
            logger.error("No se ha indicado una tarea");
            return null;
        }
        taskDto.setId(task.getId());
        taskDto.setUuid(task.getUuid());
        taskDto.setType(task.getType());
        taskDto.setResume(task.getResume());
        taskDto.setTimeTask(task.getTimeTask().toString());
        taskDto.setUser(userDto);
        taskDto.setStatus(String.valueOf(task.getIdStatus()));
        taskDto.setAssignedAt(task.getAssignedAt());
        taskDto.setResolveAt(task.getResolveAt());
        taskDto.setCreateAt(task.getCreateAt());
        taskDto.setUpdateAt(task.getUpdateAt());

        return taskDto;
    }
}
