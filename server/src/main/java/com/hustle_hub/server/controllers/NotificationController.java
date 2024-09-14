package com.hustle_hub.server.controllers;

import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.NotificationDto;
import com.hustle_hub.server.services.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/create/{userId}/{taskId}")
    public ApiResponseObject createNotification(@RequestBody NotificationDto notificationDto, @PathVariable Long userId,@PathVariable Long taskId){
        return notificationService.createNotification(taskId, userId, notificationDto);
    }

    @GetMapping("/get/{notificationId}")
    public ApiResponseObject getNotification(@PathVariable Long notificationId){
        return notificationService.getNotification(notificationId);
    }

    @GetMapping("/get/tasks/{taskId}")
    public ApiResponseData getNotificationsFOrSpecificTask(@PathVariable Long taskId){
        return notificationService.getAllNotificationsOfSpecificTask(taskId);
    }
    @GetMapping("/get/user/{userId}")
    public ApiResponseData getNotificationsFOrSpecificUser(@PathVariable Long userId){
        return notificationService.getAllNotificationsOfSpecificUser(userId);
    }
}
