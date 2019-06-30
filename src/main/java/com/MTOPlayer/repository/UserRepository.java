package com.MTOPlayer.repository;

import com.MTOPlayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLoginOrEmail(String userName, String email);
    User findUserByEmail(String email);
    User findUserById(int id);
}
