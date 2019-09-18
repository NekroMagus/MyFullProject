package com.football.manager.dao;

import com.football.manager.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Video} class.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
public interface VideoDao extends JpaRepository<Video, Long> {
}
