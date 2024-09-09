package com.hustle_hub.server.controllers;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.UserDto;
import com.hustle_hub.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ApiResponseObject createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PutMapping("/update/{userId}")
    public ApiResponseObject updateUser(@RequestBody UserDto userDto,@PathVariable Long userId){
        return userService.updateUser(userDto,userId);
    }

    @GetMapping("/get/{userId}")
    public ApiResponseObject getUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponseObject deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
