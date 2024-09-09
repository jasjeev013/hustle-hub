package com.hustle_hub.server.services;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.ProfileDto;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {

    ApiResponseObject createProfile(Long userId, ProfileDto profileDto);
    ApiResponseObject updateProfile(Long profileId,ProfileDto profileDto);
    ApiResponseObject getProfile(Long profileId);
    ApiResponseObject deleteProfile(Long profileId);

}
