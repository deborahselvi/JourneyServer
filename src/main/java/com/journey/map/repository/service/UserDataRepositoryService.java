package com.journey.map.repository.service;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepositoryService {

    public Long addUserData(Long roomId, String userName);

    public void listUserDatas();
    
    public void updateUserData(Integer userDataID, String userName);

    public void deleteUserData(Integer userDataID);

}
