package com.taskapp.util.impl;

import com.taskapp.util.TaskUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TaskUtilImpl implements TaskUtil {


    @Override
    public LocalTime getTimeOfTask(int idPriority) {
        if (idPriority>=3){
            return LocalTime.of(2,0);
        } else if (idPriority>=2) {
            return LocalTime.of(3,0);
        }else if (idPriority>=1) {
            return LocalTime.of(4,0);
        }else{
            return null;
        }

    }
}
