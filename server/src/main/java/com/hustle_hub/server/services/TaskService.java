package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.TaskDto;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    ApiResponseObject createTask(Long categoryId,TaskDto taskDto);
    ApiResponseObject updateTask(Long categoryId ,Long taskId, TaskDto taskDto);
    ApiResponseObject getTask(Long categoryId ,Long taskId);
    ApiResponseData getTasksForSpecificUser(Long categoryId);
    ApiResponseObject deleteTask(Long categoryId,Long taskId);


}
