package com.football.manager.dao;

import com.football.manager.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Photo} class.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
public interface PhotoDao extends JpaRepository<Photo,Long> {

}
