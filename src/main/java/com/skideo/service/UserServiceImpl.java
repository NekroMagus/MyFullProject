package com.skideo.service;

import com.skideo.dao.UserDao;
import com.skideo.dto.UserDto;
import com.skideo.model.User;
import com.skideo.model.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        if (userDao.findByLogin(user.getLogin()) != null) {
            throw new RuntimeException("User with login " + user.getLogin() + " is already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.UNCONFIRMED);
        userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}