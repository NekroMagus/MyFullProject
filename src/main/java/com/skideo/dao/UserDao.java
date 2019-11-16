package com.skideo.dao;

import com.skideo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);
    User findByLogin(String login);

}
