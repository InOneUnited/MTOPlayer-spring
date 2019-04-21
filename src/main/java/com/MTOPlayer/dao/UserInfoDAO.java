package com.MTOPlayer.dao;

import com.MTOPlayer.models.UserInfo;
import com.MTOPlayer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoDAO {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoDAO(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo addNewUserInfoToDB(UserInfo userInfo) {
        return this.userInfoRepository.save(userInfo);
    }
}
