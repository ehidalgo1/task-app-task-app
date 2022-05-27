package com.taskapp.mapper.impl;

import com.taskapp.dto.TaskDto;
import com.taskapp.dto.UserDto;
import com.taskapp.mapper.TaskMapper;
import com.taskapp.model.Task;
import com.taskapp.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskMapperImpl implements TaskMapper {

    Logger logger = LoggerFactory.getLogger(TaskMapperImpl.class);

    @Override
    public TaskDto taskToTaskDto(Task task, UserDto userDto) {
        TaskDto taskDto = new TaskDto();
        if (task==null || userDto == null){
            logger.error("No se ha indicado una tarea o un usuario de entrada");
            return null;
        }
        taskDto.setId(task.getId());
        taskDto.setUuid(task.getUuid());
        taskDto.setType(String.valueOf(task.getIdType()));
        taskDto.setResume(task.getResume());
        taskDto.setTimeTask(task.getTimeTask().toString());
        taskDto.setUserDto(userDto);
        taskDto.setStatus(String.valueOf(task.getIdStatus()));
        taskDto.setAssignedAt(task.getAssignedAt().toString());
        taskDto.setResolveAt(task.getResolveAt().toString());
        taskDto.setCreateAt(task.getCreateAt().toString());
        taskDto.setUpdateAt(task.getUpdateAt().toString());

        return taskDto;
    }
}
