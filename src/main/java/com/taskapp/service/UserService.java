package com.taskapp.service;

import com.taskapp.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

}
