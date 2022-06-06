package com.taskapp.controller;

import com.taskapp.model.Type;
import com.taskapp.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeController {

    private Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity getAllTypes(){
        logger.info("invocando petición getAllTypes");
        return typeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTypeById(@PathVariable int id){
        logger.info("invocando petición getTypeById");
        return typeService.getTypeById(id);
    }

    @PostMapping
    public ResponseEntity postType(@RequestBody Type type){
        logger.info("invocando petición postType");
        return typeService.saveType(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTypeB(@PathVariable int id, @RequestBody Type type){
        logger.info("invocando petición updateType");
        return typeService.updateType(id, type);
    }



}
