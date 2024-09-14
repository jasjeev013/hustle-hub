package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Notification;
import com.hustle_hub.server.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTask_Id(Long taskId);
    List<Notification> findByUser_Id(Long userId);
}
