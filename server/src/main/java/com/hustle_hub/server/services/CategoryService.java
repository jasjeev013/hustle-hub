package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    ApiResponseObject createCategory(CategoryDto categoryDto);
    ApiResponseObject updateCategory(Long categoryId,CategoryDto categoryDto);
    ApiResponseObject getCategory(Long categoryId);
    ApiResponseObject deleteCategory(Long categoryId);
}
