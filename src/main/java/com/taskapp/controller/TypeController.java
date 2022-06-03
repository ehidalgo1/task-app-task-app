package com.taskapp.controller;

import com.taskapp.model.Type;
import com.taskapp.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController {

    private Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @PostMapping
    public Type postType(@RequestBody Type type){
        logger.info("Invocando peticion postType");
        return typeService.saveType(type);
    }

}
