package com.hustle_hub.server.services.impl;

import com.hustle_hub.server.exceptions.ResourceNotFoundException;
import com.hustle_hub.server.models.Profile;
import com.hustle_hub.server.models.User;
import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.ProfileDto;
import com.hustle_hub.server.repositories.ProfileRepository;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;
    ProfileRepository profileRepository;
    ModelMapper modelMapper;

    public ProfileServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponseObject createProfile(Long userId, ProfileDto profileDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        Profile profile = modelMapper.map(profileDto,Profile.class);
        profile.setUser(user);
        user.setProfile(profile);
        Profile savedProfile =  profileRepository.save(profile);
        return new ApiResponseObject("Profile Created Successfully",true,modelMapper.map(savedProfile,ProfileDto.class));
    }

    @Override
    public ApiResponseObject updateProfile(Long profileId, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new ResourceNotFoundException("Profile", "id", profileId));

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setBio(profileDto.getBio());
        profile.setProfileImageURL(profileDto.getProfileImageURL());

        Profile updatedProfile = profileRepository.save(profile);
        return new ApiResponseObject("Updated Profile Successfully",true,modelMapper.map(updatedProfile,ProfileDto.class));
    }

    @Override
    public ApiResponseObject getProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new ResourceNotFoundException("Profile", "id", profileId));
        return new ApiResponseObject("Profile Loaded Successfully",true,modelMapper.map(profile,ProfileDto.class));
    }

    @Override
    public ApiResponseObject deleteProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new ResourceNotFoundException("Profile", "id", profileId));
        User user = userRepository.findByProfile(profile);
        user.setProfile(null);
        profile.setUser(null);
        profileRepository.deleteById(profileId);

        return new ApiResponseObject("Deleted Profile Successfully",true,null);
    }
}
