package com.football.manager.dao;

import com.football.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for {@link User} class.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
public interface UserDao extends JpaRepository<User,Long> {

   User findByEmail(String email);

   List<User> findAllByName(String name);
}
