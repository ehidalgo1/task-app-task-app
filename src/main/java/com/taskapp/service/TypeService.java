package com.taskapp.service;

import com.taskapp.model.Type;
import org.springframework.http.ResponseEntity;

public interface TypeService {

    ResponseEntity getAllTypes();

    ResponseEntity getTypeById(int id);

    ResponseEntity saveType(Type type);

    ResponseEntity updateType(int id, Type type);
}
