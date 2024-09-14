package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Notification;
import com.hustle_hub.server.models.Profile;
import com.hustle_hub.server.models.Task;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.NotificationDto;
import com.hustle_hub.server.repositories.NotificationRepository;
import com.hustle_hub.server.repositories.TaskRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class NotificationServiceImpl implements NotificationService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private NotificationRepository notificationRepository;
    private ModelMapper modelMapper;

    public NotificationServiceImpl(TaskRepository taskRepository, UserRepository userRepository, NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponseObject createNotification(Long taskId, Long userId, NotificationDto notificationDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "id", taskId));
        notificationDto.setCreated_at(new Date());
        Notification notification = modelMapper.map(notificationDto,Notification.class);
        notification.setUser(user);
        notification.setTask(task);
        Notification savedNotification = notificationRepository.save(notification);
        return new ApiResponseObject("Notification Created Successfully",true,modelMapper.map(savedNotification,NotificationDto.class));
    }

    @Override
    public ApiResponseObject updateNotification(Long notificationId, NotificationDto notificationDto) {
        return null;
    }

    @Override
    public ApiResponseObject getNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()-> new ResourceNotFoundException("Notification", "id", notificationId));
        return new ApiResponseObject("Notification Fetched Successfully",true,modelMapper.map(notification,Notification.class));
    }

    @Override
    public ApiResponseData getAllNotificationsOfSpecificTask(Long taskId) {
        List<Notification> notifications = notificationRepository.findByTask_Id(taskId);
        return new ApiResponseData("Notifications Fetched Successfully",true,notifications.stream().map(notification -> modelMapper.map(notification,NotificationDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ApiResponseData getAllNotificationsOfSpecificUser(Long userId) {
        List<Notification> notifications = notificationRepository.findByUser_Id(userId);
        return new ApiResponseData("Notifications Fetched Successfully",true,notifications.stream().map(notification -> modelMapper.map(notification,NotificationDto.class)).collect(Collectors.toList()));
    }

    @Override
    public ApiResponseObject deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
        return new ApiResponseObject("Notification Deleted Successfully",true,null);
    }
}
