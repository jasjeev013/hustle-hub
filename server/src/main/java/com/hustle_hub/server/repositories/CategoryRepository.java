package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
