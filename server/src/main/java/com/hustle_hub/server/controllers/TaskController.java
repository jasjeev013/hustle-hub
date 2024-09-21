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

    @PostMapping("/create/{categoryId}")
    public ApiResponseObject createTask(@PathVariable Long categoryId, @RequestBody TaskDto taskDto){
        return taskService.createTask(categoryId,taskDto);
    }

    @PutMapping("/update/{categoryId}/{taskId}")
    public ApiResponseObject updateTask(@PathVariable Long categoryId,@PathVariable Long taskId,@RequestBody TaskDto taskDto){
        return taskService.updateTask(categoryId,taskId,taskDto);
    }

    @GetMapping("/get/{categoryId}/{taskId}")
    public ApiResponseObject getTask(@PathVariable Long categoryId,@PathVariable Long taskId){
        return taskService.getTask(categoryId,taskId);
    }

    @GetMapping("/all/{categoryId}")
    public ApiResponseData getTasksForSpecificUser(@PathVariable Long categoryId){
        return taskService.getTasksForSpecificUser(categoryId);
    }

    @DeleteMapping("/delete/{categoryId}/{taskId}")
    public ApiResponseObject deleteTask(@PathVariable Long categoryId,@PathVariable Long taskId){
        return taskService.deleteTask(categoryId, taskId);
    }

}
