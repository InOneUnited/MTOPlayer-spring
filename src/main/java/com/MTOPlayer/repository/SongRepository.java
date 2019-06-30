package com.MTOPlayer.repository;

import com.MTOPlayer.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
