package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
