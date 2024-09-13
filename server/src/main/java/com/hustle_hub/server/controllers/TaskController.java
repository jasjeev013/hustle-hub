package com.hustle_hub.server.controllers;

import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.TaskDto;
import com.hustle_hub.server.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create/{userId}")
    public ApiResponseObject createTask(@PathVariable Long userId, @RequestBody TaskDto taskDto){
        return taskService.createTask(userId,taskDto);
    }

    @PutMapping("/update/{userId}/{taskId}")
    public ApiResponseObject updateTask(@PathVariable Long userId,@PathVariable Long taskId,@RequestBody TaskDto taskDto){
        return taskService.updateTask(userId,taskId,taskDto);
    }

    @GetMapping("/get/{userId}/{taskId}")
    public ApiResponseObject getTask(@PathVariable Long userId,@PathVariable Long taskId){
        return taskService.getTask(userId,taskId);
    }

    @GetMapping("/all/{userId}")
    public ApiResponseData getTasksForSpecificUser(@PathVariable Long userId){
        return taskService.getTasksForSpecificUser(userId);
    }

    @DeleteMapping("/delete/{userId}/{taskId}")
    public ApiResponseObject deleteTask(@PathVariable Long userId,@PathVariable Long taskId){
        return taskService.deleteTask(userId, taskId);
    }

}
