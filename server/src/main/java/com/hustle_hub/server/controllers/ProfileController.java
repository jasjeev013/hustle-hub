package com.hustle_hub.server.controllers;

import com.hustle_hub.server.payloads.ApiResponseObject;
import com.hustle_hub.server.payloads.ProfileDto;
import com.hustle_hub.server.services.ProfileService;
import com.hustle_hub.server.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create/{userId}")
    public ApiResponseObject createProfile(@RequestBody ProfileDto profileDto, @PathVariable Long userId){
        return profileService.createProfile(userId, profileDto);
    }

    @PutMapping("/update/{profileId}")
    public ApiResponseObject updateProfile(@RequestBody ProfileDto profileDto,@PathVariable Long profileId){
        return profileService.updateProfile(profileId,profileDto);
    }

    @GetMapping("/get/{profileId}")
    public ApiResponseObject getProfile(@PathVariable Long profileId){
        return profileService.getProfile(profileId);
    }

    @DeleteMapping("/delete/{profileId}")
    public ApiResponseObject deleteProfile(@PathVariable Long profileId){
        return profileService.deleteProfile(profileId);
    }

}
