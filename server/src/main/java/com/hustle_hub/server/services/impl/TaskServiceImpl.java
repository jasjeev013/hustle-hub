package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Task;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.TaskDto;
import com.hustle_hub.server.repositories.TaskRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponseObject createTask(Long userId,TaskDto taskDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Task task = modelMapper.map(taskDto,Task.class);
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return new ApiResponseObject("Task Created Successfully",true,modelMapper.map(savedTask,TaskDto.class));
    }

    @Override
    public ApiResponseObject updateTask(Long userId , Long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (task.getUser().getId() == userId){
            task.setTitle(taskDto.getTitle());
            task.setDue_date(taskDto.getDue_date());
            task.setStatus(taskDto.getStatus());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setCategories(taskDto.getCategories());
            Task updatedTask = taskRepository.save(task);
            return new ApiResponseObject("Task Updated Successfully",true,modelMapper.map(updatedTask,TaskDto.class));
        }
        return new ApiResponseObject("Task Does not exist",false,null);
    }

    @Override
    public ApiResponseObject getTask(Long userId ,Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (task.getUser().getId() == userId){
            return new ApiResponseObject("Successfully Fetched Task",true,modelMapper.map(task,TaskDto.class));
        }
        return new ApiResponseObject("Task Not Found",false,null);
    }

    @Override
    public ApiResponseData getTasksForSpecificUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        List<TaskDto> taskDtos= user.getTasks().stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
        return new ApiResponseData("Tasks Fetched Successfully", true, Collections.singletonList(taskDtos));
    }

    @Override
    public ApiResponseObject deleteTask(Long userId,Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (task.getUser().getId() == userId){
//            user.setTasks(user.getTasks().stream().filter(task1 -> task1.getId()!=taskId).collect(Collectors.toList()));
            taskRepository.deleteById(taskId);

            return new ApiResponseObject("Task Deleted Successfully",true,null);
        }
        return new ApiResponseObject("Task Not Found",false,null);

    }
}
