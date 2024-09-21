package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Category;
import com.hustle_hub.server.models.Task;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.TaskDto;
import com.hustle_hub.server.repositories.CategoryRepository;
import com.hustle_hub.server.repositories.TaskRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponseObject createTask(Long categoryId,TaskDto taskDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        Task task = modelMapper.map(taskDto,Task.class);
        task.setCategory(category);
        Task savedTask = taskRepository.save(task);
        return new ApiResponseObject("Task Created Successfully",true,modelMapper.map(savedTask,TaskDto.class));
    }

    @Override
    public ApiResponseObject updateTask(Long categoryId , Long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (Objects.equals(task.getCategory().getId(), categoryId)){
            task.setTitle(taskDto.getTitle());
            task.setDue_date(taskDto.getDue_date());
            task.setStatus(taskDto.getStatus());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            Task updatedTask = taskRepository.save(task);
            return new ApiResponseObject("Task Updated Successfully",true,modelMapper.map(updatedTask,TaskDto.class));
        }
        return new ApiResponseObject("Task Does not exist",false,null);
    }

    @Override
    public ApiResponseObject getTask(Long categoryId ,Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (Objects.equals(task.getCategory().getId(), categoryId)){
            return new ApiResponseObject("Successfully Fetched Task",true,modelMapper.map(task,TaskDto.class));
        }
        return new ApiResponseObject("Task Not Found",false,null);
    }

    @Override
    public ApiResponseData getTasksForSpecificUser(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        List<TaskDto> taskDtos= category.getTasks().stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
        return new ApiResponseData("Tasks Fetched Successfully", true, Collections.singletonList(taskDtos));
    }

    @Override
    public ApiResponseObject deleteTask(Long categoryId,Long taskId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        if (Objects.equals(task.getCategory().getId(), categoryId)){
//            user.setTasks(user.getTasks().stream().filter(task1 -> task1.getId()!=taskId).collect(Collectors.toList()));
            taskRepository.deleteById(taskId);
            return new ApiResponseObject("Task Deleted Successfully",true,null);
        }
        return new ApiResponseObject("Task Not Found",false,null);

    }
}
