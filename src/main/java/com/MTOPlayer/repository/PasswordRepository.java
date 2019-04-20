package com.MTOPlayer.repository;

import com.MTOPlayer.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
