package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    void deleteUser(Integer userId);



}
