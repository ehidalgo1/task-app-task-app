package com.taskapp.repository;

import com.taskapp.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<Type, Integer> {
    Type findByType(String type);
}
