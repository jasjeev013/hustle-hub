package com.hustle_hub.server.scheduler;

import com.hustle_hub.server.models.User;
import com.hustle_hub.server.repositories.UserRepository;
import com.hustle_hub.server.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Scheduled(cron = "0 0 0 ? * * *")
    void fetchUsersAndScheduleEmail() {
        List<User> users = userRepository.findAll();
        for (User user:users){
//            emailService.
        }
    }
}
