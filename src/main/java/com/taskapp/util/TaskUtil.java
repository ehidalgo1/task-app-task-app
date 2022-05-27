package com.taskapp.util;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public interface TaskUtil {

    LocalTime getTimeOfTask(int idPriority);

}
