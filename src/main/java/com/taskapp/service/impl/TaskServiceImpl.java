package com.taskapp.service.impl;

import com.taskapp.dto.TaskDto;
import com.taskapp.dto.UserDto;
import com.taskapp.mapper.TaskMapper;
import com.taskapp.model.Task;
import com.taskapp.model.Type;
import com.taskapp.repository.TaskRepository;
import com.taskapp.repository.TypeRepository;
import com.taskapp.response.Response;
import com.taskapp.service.TaskService;
import com.taskapp.util.Constant;
import com.taskapp.util.StatusEnum;
import com.taskapp.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.MacSpi;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskUtil taskUtil;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity findAll() {
        List<Task> tasks = null;
        Response response = null;
        String msg = null;
        try {
            tasks = (List<Task>) taskRepository.findAll();
        }catch (Exception e){
            msg = "Ha ocurrido un error de servidor";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TASKS_LIST, tasks);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity findByUuid(String uuid) {
        TaskDto taskResp = null;
        UserDto userDto = null;
        Response response = null;
        String msg = null;
        try{
            logger.info("uuid recibido: "+uuid);
            if (uuid==null || uuid.isEmpty()){
                msg="El uuid no puede ser nulo o vacio";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            logger.info("buscando tarea con uuid:"+uuid);
            Task task = taskRepository.findByUuid(uuid);
            if(task==null){
                msg="la tarea no existe";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("Tarea obtenida: "+task);

            if (task.getIdUser() != null && task.getIdUser() >0 ){

                Map<String, Long> pathVariable = new HashMap<>();
                pathVariable.put("id", task.getIdUser());
                logger.info("buscando el usuario con id: "+task.getIdUser());
                logger.info("invocando microservicio http://localhost:8090/api/user/id/"+pathVariable.get("id"));
                userDto = restTemplate.getForObject("http://localhost:8090/api/user/id/{id}", UserDto.class, pathVariable);
                if(userDto==null){
                    msg = "No se ha encontrado el usuario con id: "+pathVariable.get("id");
                    logger.info(msg);
                }
                logger.info("usuario obtenido: "+userDto);
            }
            taskResp = taskMapper.taskToTaskDto(task, userDto);
            logger.info("Response: "+taskResp);
        }catch (Exception ex){
            msg="ha ocurrido un error de servidor";
            logger.error(msg, ex);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TASK, taskResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateTask(String uuid, Task task) {
        Task taskResp = null;
        Response response = null;
        String msg = null;
        try {
            if (uuid == null || uuid.isEmpty()) {
                msg = "no se ha indicado un uuid";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            } else if (task == null) {
                msg = "no se ha indicado una tarea";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            } else if (task.getType() == null || task.getType().getType() == null || task.getType().getType().isEmpty()) {
                msg = "no se ha indicado un tipo de tarea";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            logger.info("buscando tarea con uuid: "+uuid);
            Task taskUp = taskRepository.findByUuid(uuid);
            if (taskUp==null) {
                msg = "la tarea no existe";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tarea obtenida: "+taskUp);
            logger.info("buscando el tipo "+task.getType().getType());
            Type type = typeRepository.findByType(task.getType().getType());
            if (type==null){
                msg = "el tipo "+ task.getType().getType() + " no existe";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tipo obtenido: "+type);
            taskUp.setType(type);
            taskUp.setIdPriority(task.getIdPriority());
            taskUp.setTimeTask(taskUtil.getTimeOfTask(task.getIdPriority()));
            try{
                taskUp.setIdUser(task.getIdUser());
            }catch (NullPointerException e){
                taskUp.setIdUser(0L);
            }
            taskUp.setIdStatus(task.getIdStatus());
            taskUp.setResume(task.getResume());
            taskUp.setUpdateAt(LocalDateTime.now());
            taskUp.setResolveAt(task.getResolveAt());
            taskResp = taskRepository.save(taskUp);
            logger.info("se ha actualizado la tarea con uuid: "+uuid);
        }catch (DataAccessException e){
            msg = "ocurrio un error al acceder a la base de datos";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            msg = "ocurrio un error de servidor";
            logger.error(msg, ex);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TASK, taskResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity assignTask(String uuidTask, String uuidUser) {
        Task taskAssigned = null;
        Response response = null;
        String msg = null;
        try {

            if(uuidTask == null || uuidTask.isEmpty()){
                msg = "no se ha enviado un uuid de tarea";
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }else if(uuidUser == null || uuidUser.isEmpty() ) {
                msg = "no se ha enviado un uuid de usuario";
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }

            Task task = taskRepository.findByUuid(uuidTask);
            if(task == null) {
                msg = "la tarea con uuid: "+uuidTask+" no existe";
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tarea obtenida: "+task);
            Map<String, String> pathVariable = new HashMap<>();
            pathVariable.put("uuid", uuidUser);
            UserDto userDto = restTemplate.getForObject("http://localhost:8090/api/user/{uuid}", UserDto.class, pathVariable);
            if (userDto==null){
                msg = "el usuario con uuid: "+uuidUser+" no existe";
                logger.info(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("usuario obtenido: "+userDto);
            task.setIdUser(userDto.getId());
            taskAssigned = taskRepository.save(task);
        }catch (DataAccessException e){
            msg = "ocurrio un error al consultar a la base de datos";
            logger.info(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            msg = "ocurrio un error de servidor";
            logger.info(msg, ex);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("se ha asignado tarea a usuario: "+taskAssigned);
        response = new Response(StatusEnum.OK, Constant.TASK, taskAssigned);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveTask(Task task) {
        Task taskResp = null;
        Response response = null;
        String msg = null;
        logger.info("Request: "+task);
        if (task==null){
            msg = "No se ha recibido ninguna tarea";
            logger.error(msg);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else if (task.getType() == null || task.getType().getType()==null || task.getType().getType().isEmpty()) {
            msg = "No se ha recibido ningun tipo";
            logger.error(msg);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        Type type = null;
        try {
            type = typeRepository.findByType(task.getType().getType());
            if(type==null){
                msg = "el typo "+task.getType().getType()+" no existe";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            task.setType(type);
            try{
                Long idUser = task.getIdUser();
                task.setIdUser(idUser);
            }catch (NullPointerException e){
                task.setIdUser(0L);
            }
            task.setUuid(UUID.randomUUID().toString());
            task.setTimeTask(taskUtil.getTimeOfTask(task.getIdPriority()));
            task.setCreateAt(LocalDateTime.now());
            logger.info("Task: "+task);
            taskResp = taskRepository.save(task);
        }catch (NullPointerException e){
            msg = "el tipo "+task.getType().getType()+" no existe";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            msg = "ocurrio un error de servidor";
            logger.error(msg, ex);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response: "+taskResp);
        response = new Response(StatusEnum.OK, Constant.TASK, taskResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity deleteTask(String uuid) {
        Task taskResp = null;
        String msg = null;
        Response response = null;
        try {
            if (uuid == null || uuid.isEmpty()){
                msg = "el uuid no puede ser vacio";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            logger.info("buscando tarea con uuid:"+uuid);
            taskResp = taskRepository.findByUuid(uuid);
            if(taskResp==null){
                msg = "La tarea no existe";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tarea obtenida: "+taskResp);
            taskRepository.delete(taskResp);
            logger.info("Se ha eliminado la tarea: "+taskResp);
        }catch (DataAccessException e){
            msg = "Ha ocurrido un error de base de datos";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            msg = "Ha ocurrido un error de servidor";
            logger.error(msg, ex);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TASK, taskResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
