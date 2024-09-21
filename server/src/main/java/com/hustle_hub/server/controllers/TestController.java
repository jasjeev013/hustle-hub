package com.hustle_hub.server.controllers;

import com.hustle_hub.server.payloads.ApiResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/")
    public ApiResponseObject test(){
        return new ApiResponseObject("THe API is running fine",true,null);
    }
}
