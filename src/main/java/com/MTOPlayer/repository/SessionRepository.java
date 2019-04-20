package com.MTOPlayer.repository;


import com.MTOPlayer.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
