package com.journey.map.service.provider;

import com.journey.map.repository.service.UserDataRepositoryService;
import com.journey.map.service.UserDataService;

import org.springframework.stereotype.Service;

@Service
public class UserDataServiceProvider implements UserDataService {
    private final UserDataRepositoryService userDataRepositoryService;
    public UserDataServiceProvider(UserDataRepositoryService userDataRepositoryService) {
        this.userDataRepositoryService = userDataRepositoryService;
    }

    /* Save user data to repository */
    @Override
    public Long saveUserData(Long roomId, String userName) {
        return userDataRepositoryService.addUserData(roomId, userName);
    }
}
