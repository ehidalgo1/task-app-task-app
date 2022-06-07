package com.taskapp.service.impl;

import com.taskapp.model.Type;
import com.taskapp.repository.TypeRepository;
import com.taskapp.response.Response;
import com.taskapp.service.TypeService;
import com.taskapp.util.Constant;
import com.taskapp.util.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    private Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);

    @Override
    public ResponseEntity getAllTypes() {
        List<Type> types = null;
        Response response = null;
        String msg = null;
        try{
            logger.info("obteniendo todos los tipos");
            types = (List<Type>) typeRepository.findAll();
            if (types!= null){
                logger.info("total: "+types.size());
            }
        }catch (Exception e){
            msg = "Ha ocurrido un error de servidor";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TYPES_LIST, types);
        logger.info("response: "+response);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getTypeById(int id) {
        logger.info("request: "+id);
        Type type = null;
        Response response = null;
        String msg = null;
        try {
            logger.info("buscando el tipo con id: "+id);
            type = typeRepository.findById(id).get();
            if (type==null){
                msg = "El tipo no existe";
                logger.warn(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tipo obtenido:"+type);
        }catch (Exception e){
            msg = "Ha ocurrido un error de servidor";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = new Response(StatusEnum.OK, Constant.TYPE, type);
        logger.info("response: "+response);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveType(Type type) {
        logger.info("request: ",type);
        Type typeResp = null;
        Response response = null;
        String msg = null;
        if(type==null){
            msg = "No se ha recibido un tipo";
            logger.error(msg);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try{
            typeResp = typeRepository.save(type);
            logger.info("se ha guardado el tipo: "+typeResp);
        }catch (DataAccessException ex){
            msg = "ha ocurrido un error de servidor";
            logger.error(msg);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response: "+typeResp);
        response = new Response(StatusEnum.OK, Constant.TYPE, typeResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateType(int id, Type type) {
        logger.info("request: id: "+id+" tipo: "+ type);
        Type typeResp = null;
        Type typeFind = null;
        Response response = null;
        String msg = null;
        try {
            if (type.getType() == null || type.getType().isEmpty()){
                msg = "El tipo no puede ser vacio";
                logger.error(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            typeFind = typeRepository.findById(id).get();
            if (typeFind==null){
                msg = "El tipo no existe";
                logger.warn(msg);
                response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            logger.info("tipo obtenido: "+ typeFind);
            typeFind.setType(type.getType());
            typeResp = typeRepository.save(typeFind);
            logger.info("typo actualizado: "+typeResp);
        }catch (Exception e){
            msg = "ha ocurrido un error de servidor";
            logger.error(msg, e);
            response = new Response(StatusEnum.ERROR, Constant.MESSAGE_ERROR, msg);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response: "+typeResp);
        response = new Response(StatusEnum.OK, Constant.TYPE, typeResp);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}