package com.taskapp.service.impl;

import com.taskapp.dto.UserDto;
import com.taskapp.model.Task;
import com.taskapp.repository.TaskRepository;
import com.taskapp.service.TaskService;
import com.taskapp.util.TaskUtil;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.rmi.CORBA.Util;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskUtil taskUtil;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task findByUuid(String uuid) {
        if (uuid==null || uuid.isEmpty()){
            logger.warn("El uuid de task no puede ser nulo o vacio");
            return null;
        }
        return taskRepository.findByUuid(uuid);
    }

    @Override
    public Task updateTask(String uuid, Task task) {
        Task taskUp = null;
        try {
            if (uuid == null || uuid.isEmpty() || task == null) {
                logger.warn("no se ha recibido el uuid o la tarea");
                return null;
            }
            logger.info("buscando por id: "+uuid);
            taskUp = taskRepository.findByUuid(uuid);
            logger.info("task encontrada: "+taskUp);
            if (taskUp==null) {
                logger.info("la tarea con uuid: ".concat(uuid).concat(" No existe"));
                return null;
            }
            taskUp.setIdType(task.getIdType());
            taskUp.setIdPriority(task.getIdPriority());
            taskUp.setTimeTask(taskUtil.getTimeOfTask(task.getIdPriority()));
            taskUp.setIdUser(task.getIdUser());
            taskUp.setIdStatus(task.getIdStatus());
            taskUp.setResume(task.getResume());
            taskUp.setUpdateAt(task.getUpdateAt());
            taskUp.setResolveAt(task.getResolveAt());
            taskUp = taskRepository.save(taskUp);

        }catch (DataAccessException ex){
            logger.error("ocurrio un error", ex.getLocalizedMessage());
        }catch (Exception e){
            logger.error("ocurrio un error", e.getLocalizedMessage());
        }
        return taskUp;
    }

    @Override
    public Task assignTask(String uuidTask, String uuidUser) {
        if(uuidTask == null || uuidTask.isEmpty() ||  uuidUser == null || uuidUser.isEmpty() ) {
            logger.warn("el uuid de task o uuid no puede ser nulo o vacío");
            return  null;
        }

        Task task = taskRepository.findByUuid(uuidTask);
        if(task == null) {
            logger.warn("la tarea no existe");
            return null;
        }
        logger.info("Task: "+task);
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("uuid", uuidUser);
        UserDto userDto = restTemplate.getForObject("http://localhost:8090/api/user/{uuid}", UserDto.class, pathVariable);
        if (userDto==null){
            logger.info("No se ha encontrado el usuario con uuiid "+uuidUser);
            return null;
        }
        task.setIdUser(userDto.getId());
        logger.info("User: "+userDto);
        return taskRepository.save(task);
    }

    @Override
    public Task saveTask(Task task) {
        if (task==null){
            logger.warn("No se ha recibido ninguna Task");
            return null;
        }
        task.setUuid(UUID.randomUUID().toString());
        task.setTimeTask(taskUtil.getTimeOfTask(task.getIdPriority()));
        task.setCreateAt(new Date());
        logger.info("Task: "+task);
        return taskRepository.save(task);
    }

}
