package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    ApiResponseObject createNotification(Long taskId,Long userId,NotificationDto notificationDto);
    ApiResponseObject updateNotification(Long notificationId,NotificationDto notificationDto);
    ApiResponseObject getNotification(Long notificationId);
    ApiResponseData getAllNotificationsOfSpecificTask(Long taskId);
    ApiResponseData getAllNotificationsOfSpecificUser(Long userId);
    ApiResponseObject deleteNotification(Long notificationId);


}
