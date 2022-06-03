package com.taskapp.service.impl;

import com.taskapp.model.Type;
import com.taskapp.repository.TypeRepository;
import com.taskapp.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    private Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);

    @Override
    public Type saveType(Type type) {
        logger.info("Request: "+type);
        if(type==null){
            logger.error("No se ha recibido un tipo");
        }
        Type typeResp = null;
        try{
            typeResp = typeRepository.save(type);
        }catch (DataAccessException ex){
            logger.error("Se ha producido un error al guardar el tipo: ", ex.getLocalizedMessage());
            return null;
        }
        logger.info("Response: "+typeResp);
        return typeResp;
    }


}