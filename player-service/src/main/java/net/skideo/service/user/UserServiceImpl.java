package net.skideo.service.user;

import net.skideo.dao.UserDao;
import net.skideo.dao.VideoDao;
import net.skideo.dto.UserDto;
import net.skideo.dto.UserProfileDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Video;
import net.skideo.exception.UserExistsException;
import net.skideo.exception.UserNotFoundException;
import net.skideo.model.User;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.RoleFootball;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

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
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRoleFootball(userDto.getRoleFootball());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setBirthDate(userDto.getBirthDate());
        user.setCountry(userDto.getCountry());
        user.setCity(userDto.getCity());
        user.setLinkSocialNetwork(userDto.getLinkSocialNetwork());
        user.setLeadingLeg(userDto.getLeadingLeg());
        user.setRolePeople(userDto.getRolePeople());
        user.setAgent(userDto.isAgent());
        dao.save(user);
        return new UserDto(user);
    }

    @Override
    public List<User> findByBirthDateBetween(LocalDate birth, LocalDate now) {
        return dao.findByBirthDateBetween(birth, now);
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
    public List<User> findByBirthDateBetweenAndRoleFootballAndCountry(LocalDate birth, LocalDate now,
                                                                        RoleFootball roleFootball, String country) {
        return dao.findByBirthDateBetweenAndRoleFootballAndCountry(birth, now, roleFootball, country);
    }

    @Override
    public List<User> findByBirthDateBetweenAndRoleFootball(LocalDate birth, LocalDate now, RoleFootball roleFootball) {
        return dao.findByBirthDateBetweenAndRoleFootball(birth, now, roleFootball);
    }

    @Override
    public List<User> findByBirthDateBetweenAndCountry(LocalDate birth, LocalDate now, String country) {
        return dao.findByBirthDateBetweenAndCountry(birth, now, country);
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
    public UserProfileDto getProfile() {
        final User CURRENT_USER = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return new UserProfileDto(CURRENT_USER);
    }


}