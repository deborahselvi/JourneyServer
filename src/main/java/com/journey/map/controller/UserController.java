package com.journey.map.controller;

import com.journey.map.model.UserData;
import com.journey.map.service.UserDataService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserDataService userDataService;
    public UserController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("addUserToRoom")
    public Long addUserToRoom(@RequestBody UserData userData) {
        return userDataService.saveUserData(userData.getRoomId(), userData.getUserName());
    }
}
