package com.taskapp.util;

import com.taskapp.dto.UserDto;
import com.taskapp.model.Task;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UtilTest {

    public static final List<Task> TASK_LIST = Arrays.asList(
            new Task(1L, UUID.randomUUID().toString(), 1, 1L, "barrer", 1,
                    1, LocalTime.of(2,0), new Date(), null, null, null),
            new Task(2L, UUID.randomUUID().toString(), 1, 2L, "aspirar", 2,
                    1, LocalTime.of(2,0), new Date(), null, null, null),
            new Task(3L, UUID.randomUUID().toString(), 1, 1L, "trapear", 2,
                    1, LocalTime.of(3,0), new Date(), null, null, null)
    );

    public static final Task TASK = new Task(1L, UUID.randomUUID().toString(), 1, 1L, "barrer", 1,
            1, LocalTime.of(2,0), new Date(), null, null, null);


    public static final UserDto USER_DTO = new UserDto(1L, UUID.randomUUID().toString(),
            "john", "doe", "jdoe" , new Date(), null);



    public static final List<UserDto> USER_DTO_LIST = Arrays.asList(
            new UserDto(1L, UUID.randomUUID().toString(), "john", "doe", "jdoe" , new Date(), null),
            new UserDto(1L, UUID.randomUUID().toString(), "mariane", "hill", "mhill", new Date(), null),
            new UserDto(1L, UUID.randomUUID().toString(), "steve", "rogers", "srogers", new Date(), null)
    );


}
