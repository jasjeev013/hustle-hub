package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Profile;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.UserDto;
import com.hustle_hub.server.repositories.ProfileRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponseObject createUser(UserDto userDto) {
        User newUser = modelMapper.map(userDto,User.class);
        String hashPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashPassword);
        User savedUser = userRepository.save(newUser);
        return new ApiResponseObject("User Created Successfully",true,modelMapper.map(savedUser,UserDto.class));
    }

    @Override
    public ApiResponseObject updateUser(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);

        return new ApiResponseObject("User updated successfully",true,modelMapper.map(updatedUser,UserDto.class));

    }


    @Override
    public ApiResponseObject getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        return new ApiResponseObject("User fetched succesfully",true,modelMapper.map(user,UserDto.class));
    }

    @Override
    public ApiResponseObject deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Profile profile = user.getProfile();
        user.setProfile(null);
        profileRepository.delete(profile);
        userRepository.delete(user);
        return new ApiResponseObject("User Deleted Successfully",true,null);
    }
}
