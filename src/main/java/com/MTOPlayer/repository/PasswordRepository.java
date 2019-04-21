package com.MTOPlayer.repository;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    @Query("SELECT p FROM Password p WHERE p.user.id = :userId")
    Password findPasswordByUserId(@Param("userId") int userId);
    Password findPasswordById(int passwordId);
}
