package net.skideo.service;

import data.service.dao.UserDao;
import data.service.dto.RatingDto;
import data.service.dto.UserDto;
import data.service.dto.VideoDto;
import data.service.model.role.RolePeople;
import net.skideo.exception.UserExistsException;
import net.skideo.exception.UserNotFoundException;
import data.service.model.User;
import data.service.model.role.Role;
import data.service.model.role.RoleFootball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    Logger log = Logger.getLogger(UserServiceImpl.class.getName());

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
        user.setLeadingLeg(userDto.getLeadingLeg());
        user.setRolePeople(userDto.getRolePeople());
        user.setClub(userDto.getClub());
        user.setAgent(userDto.isAgent());
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

    @Override
    public List<VideoDto> findVideos(User currentUser) {
        Pageable pageable = PageRequest.of(0,15);
        Iterator<User> users = dao.findAll(pageable).iterator();
        List<VideoDto> videos = new LinkedList<>();

        while(users.hasNext()) {
              User user = users.next();
              if(user.equals(currentUser)) {
                  users.remove();
              }
              else {
                  videos.add(new VideoDto(user));
              }
        }

        return videos;
    }

    @Override
    public void updateRating(RatingDto ratingDto) {
         final User CURRENT_USER = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
         User user = findById(ratingDto.getId());
         if(ratingDto.getRating()<=5 && (CURRENT_USER.getRolePeople()== RolePeople.AMATEUR && user.getRolePeople()==RolePeople.PROFESSIONAL) ||
            (CURRENT_USER.getRolePeople()==RolePeople.PROFESSIONAL && user.getRolePeople()==RolePeople.AMATEUR)) {
             user.setRating(user.getRating()+ratingDto.getRating());
             user.getList().add(CURRENT_USER);
             dao.save(user);
         }
    }

    public int getRating(long id) {
        User user = findById(id);
        return user.getRating()/user.getList().size();
    }
}