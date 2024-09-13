package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.CategoryDto;
import com.hustle_hub.server.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public ApiResponseObject createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ApiResponseObject updateCategory(Long categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ApiResponseObject getCategory(Long categoryId) {
        return null;
    }

    @Override
    public ApiResponseObject deleteCategory(Long categoryId) {
        return null;
    }
}
