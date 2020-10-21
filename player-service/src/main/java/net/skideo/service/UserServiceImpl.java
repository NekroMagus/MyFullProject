package net.skideo.service;

import data.service.dao.UserDao;
import data.service.dto.UserDto;
import net.skideo.exception.UserExistsException;
import net.skideo.exception.UserNotFoundException;
import data.service.model.User;
import data.service.model.role.Role;
import data.service.model.role.RoleFootball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        if (dao.findByLogin(user.getLogin()) != null) {
            throw new UserExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.UNCONFIRMED);
        user.setActive(true);
        user.setDateOfRegistration(Timestamp.valueOf(LocalDateTime.now()));
        dao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        User user = dao.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findById(long id) {
        User user = dao.findById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return dao.findById(id);
    }

    @Override
    public UserDto editUser(UserDto userDto, String login) {
        User user = findByLogin(login);
        user.setEmail(userDto.getEmail());
        user.setVideo(userDto.getVideo());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRoleFootball(userDto.getRoleFootball());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setCountry(userDto.getCountry());
        user.setCity(userDto.getCity());
        user.setSocialNetwork(userDto.getSocialNetwork());
        dao.save(user);
        return new UserDto(user);
    }

    @Override
    public List<User> findByDateOfBirthBetween(LocalDate birth, LocalDate now) {
        return dao.findByDateOfBirthBetween(birth, now);
    }

    @Override
    public List<User> findByRoleFootball(RoleFootball roleFootball) {
        return dao.findByRoleFootball(roleFootball);
    }

    @Override
    public List<User> findByCountry(String country) {
        return findByCountry(country);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                        RoleFootball roleFootball, String country) {
        return dao.findByDateOfBirthBetweenAndRoleFootballAndCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return dao.findByDateOfBirthBetweenAndRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<User> findByDateOfBirthBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return dao.findByDateOfBirthBetweenAndCountry(birth, now, country);
    }

    @Override
    public List<User> findByRoleFootballAndCountry(RoleFootball roleFootball, String country) {
        return dao.findByRoleFootballAndCountry(roleFootball, country);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void addVideo(String link) {
        User user = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setVideo(link);
        dao.save(user);
    }



}