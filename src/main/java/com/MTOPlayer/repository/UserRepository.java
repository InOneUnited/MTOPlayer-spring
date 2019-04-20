package com.MTOPlayer.repository;

import com.MTOPlayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
