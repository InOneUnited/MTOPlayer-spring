package com.MTOPlayer.repository;

import com.MTOPlayer.models.APIKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIKeyRepository extends JpaRepository<APIKey, Long> {
}
