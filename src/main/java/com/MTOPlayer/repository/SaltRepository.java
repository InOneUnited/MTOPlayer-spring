package com.MTOPlayer.repository;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.Salt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaltRepository extends JpaRepository<Salt, Long> {
    Salt findSaltByPassword(Password password);
}
