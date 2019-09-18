package com.football.manager.dao;

import com.football.manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} class.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
public interface UserDao extends JpaRepository<User,Long> {

   User findByUsername(String username);
}
