package com.taskapp.service.impl;

import com.taskapp.dto.UserDto;
import com.taskapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtoList = null;
        try{
            userDtoList = restTemplate.getForObject("http://localhost:8090/api/user", List.class);
        }catch (Exception ex){
            logger.error("Ha ocurrido un error al obtener el usuario", ex);
        }
        return userDtoList;
    }
}
