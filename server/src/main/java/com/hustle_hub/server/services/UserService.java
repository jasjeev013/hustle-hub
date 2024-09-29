package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ApiResponseObject createUser(UserDto userDto);

    ApiResponseObject updateUser(UserDto userDto, Long userId);

    ApiResponseObject getUserById(Long userId);
    ApiResponseObject deleteUser(Long userId);

    ApiResponseObject getUserByEmail(String emailId);



}
