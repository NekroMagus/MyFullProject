package com.skideo.service;

import com.skideo.dao.UserDao;
import com.skideo.dto.UserDto;
import com.skideo.exception.UserExistsException;
import com.skideo.exception.UserNotFoundException;
import com.skideo.model.User;
import com.skideo.model.role.Role;
import com.skideo.model.role.RoleFootball;
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
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userDao.findById(id);
    }

    @Override
    public void editUser(UserDto userDto) {
        User user = findByLogin(userDto.getLogin());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRoleFootball(userDto.getRoleFootball());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setCountry(userDto.getCountry());
        user.setCity(userDto.getCity());
        user.setSocialNetwork(userDto.getSocialNetwork());
        userDao.save(user);
    }

    @Override
    public List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now) {
        return userDao.findByDateOfBirthBetween(birth, now);
    }

    @Override
    public List<User> findByRoleFootball(RoleFootball roleFootball) {
        return userDao.findByRoleFootball(roleFootball);
    }

    @Override
    public List<User> findByCountry(String country) {
        return findByCountry(country);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                        RoleFootball roleFootball, String country) {
        return userDao.findByDateOfBirthBetweenAndRoleFootballAndCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return userDao.findByDateOfBirthBetweenAndRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return userDao.findByDateOfBirthBetweenAndCountry(birth, now, country);
    }

    @Override
    public List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return userDao.findByRoleFootballAndCountry(roleFootball, country);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    private boolean userExists(User user) {
        return user == null;
    }
}