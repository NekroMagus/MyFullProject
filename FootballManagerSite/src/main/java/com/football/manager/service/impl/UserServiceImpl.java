package com.football.manager.service.impl;

import com.football.manager.dao.UserDao;
import com.football.manager.model.User;
import com.football.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link com.football.manager.service.UserService} interface.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<User> findUsersByName(String name) {
        return dao.findAllByName(name);
    }
}
