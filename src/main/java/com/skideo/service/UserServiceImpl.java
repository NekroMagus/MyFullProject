package com.skideo.service;

import com.skideo.dao.UserDao;
import com.skideo.dto.UserDto;
import com.skideo.exception.UserExistsException;
import com.skideo.exception.UserNotFoundException;
import com.skideo.model.User;
import com.skideo.model.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            throw new UserExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.UNCONFIRMED);
        user.setActive(true);
        user.setDateOfRegistration(Timestamp.valueOf(LocalDateTime.now()));
        userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        User user = userDao.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findById(long id) {
        User user = userDao.findById(id);
        if(user == null) {
            throw new UserNotFoundException();
        }
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    private boolean userExists(User user){
        return user == null;
    }
}