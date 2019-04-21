package com.MTOPlayer.dao;

import com.MTOPlayer.models.User;
import com.MTOPlayer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {

    @Autowired
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isNewUserInDB(String login, String email) {
        return this.userRepository.existsUserByLoginOrEmail(login, email);
    }

    public User getUserById(int id){
        return userRepository.findUserById(id);
    }
    public User addNewUserToDB(User user) {
        return this.userRepository.save(user);
    }


    public int getUserId(String email) {
        return this.userRepository.findUserByEmail(email).getId();
    }

    public boolean isEmailInDB(String email) {
        return this.userRepository.findUserByEmail(email) != null;
    }

}
