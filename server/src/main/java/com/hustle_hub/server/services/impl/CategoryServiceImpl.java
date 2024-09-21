package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Category;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.CategoryDto;
import com.hustle_hub.server.repositories.CategoryRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponseObject createCategory(Long userId, CategoryDto categoryDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Category category = modelMapper.map(categoryDto,Category.class);
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);
        return new ApiResponseObject("Category Created SuccessFully",true,modelMapper.map(savedCategory, CategoryDto.class));
    }

    @Override
    public ApiResponseObject updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        category.setName(categoryDto.getName());
        category.setColor(category.getColor());
        Category updatedCategory = categoryRepository.save(category);
        return new ApiResponseObject("Category Updated",true,modelMapper.map(updatedCategory, CategoryDto.class));
    }

    @Override
    public ApiResponseObject getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        return new ApiResponseObject("Category Fetched Successfully",true,modelMapper.map(category, CategoryDto.class));
    }

    @Override
    public ApiResponseData getAllCategoriesForSpecificUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        List<CategoryDto> categoryDtos = user.getCategories().stream().map(cet -> modelMapper.map(cet, CategoryDto.class)).toList();
        return new ApiResponseData("Categories Fetched Successfully",true, Collections.singletonList(categoryDtos));
    }

    @Override
    public ApiResponseObject deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.deleteById(categoryId);
        return new ApiResponseObject("Category Deleted Successfully",true,null);
    }
}
