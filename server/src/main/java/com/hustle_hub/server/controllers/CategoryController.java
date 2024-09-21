package com.hustle_hub.server.controllers;


import com.hustle_hub.server.payloads.ApiResponseData;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.CategoryDto;
import com.hustle_hub.server.services.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/create/{userId}")
    public ApiResponseObject createCategory(@PathVariable Long userId, @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(userId,categoryDto);
    }

    @PutMapping("/update/{categoryId}")
    public ApiResponseObject updateCategory(@PathVariable Long categoryId,@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryId, categoryDto);
    }

    @GetMapping("/get/{categoryId}")
    public ApiResponseObject getCategory(@PathVariable Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/get/all/{userId}")
    public ApiResponseData getAllCategoriesForSpecificUser(@PathVariable Long userId){
        return categoryService.getAllCategoriesForSpecificUser(userId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ApiResponseObject deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
