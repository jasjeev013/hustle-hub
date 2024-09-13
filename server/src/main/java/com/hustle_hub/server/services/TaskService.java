package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.TaskDto;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    ApiResponseObject createTask(Long userId,TaskDto taskDto);
    ApiResponseObject updateTask(Long userId ,Long taskId, TaskDto taskDto);
    ApiResponseObject getTask(Long userId ,Long taskId);
    ApiResponseData getTasksForSpecificUser(Long userId);
    ApiResponseObject deleteTask(Long userId,Long taskId);


}
