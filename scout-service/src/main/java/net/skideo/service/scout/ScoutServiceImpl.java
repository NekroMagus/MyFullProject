package net.skideo.service.scout;


import net.skideo.dao.ScoutDao;
import net.skideo.dao.UserDao;
import net.skideo.dao.VideoDao;
import net.skideo.dto.ProfileDto;
import net.skideo.dto.ProfileUserDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ScoutServiceImpl implements ScoutService {

    @Autowired
    private ScoutDao scoutDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Scout findById(long id) {
        return scoutDao.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
                );
    }

    @Override
    public Scout findByLogin(String login) {
        return scoutDao.findByLogin(login);
    }

    @Override
    public void save(Scout scout) {
        scout.setPassword(encoder.encode(scout.getPassword()));
        scoutDao.save(scout);
    }

    @Override
    public ProfileDto getProfile() {
        final Scout CURRENT_SCOUT = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        ProfileDto profile = new ProfileDto(CURRENT_SCOUT);
        List<ProfileUserDto> players = new LinkedList<>();
        List<User> users = userDao.findAll();

        if(users.size()>=1) {

            for (int i = 1; i <= 3; i++) {

                int random = (int) (Math.random() * users.size()-1);
                User user = users.get(random);
                List<Video> videos = getVideos(user);

                if(videos.size()>=1) {
                    players.add(new ProfileUserDto(user));
                }

            }

        }
        profile.setPlayers(players);

        return profile;
    }

    @Override
    public void updateProfile(UpdateProfileDto dto) {
         Scout scout = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
         scout.setName(dto.getName());
         scout.setSurname(dto.getSurname());
         save(scout);
    }

    @Override
    public List<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth) {
        Pageable page = PageRequest.of(0,15);
        List<SearchDto> users = new LinkedList<>();
        Iterator<User> iterator = userDao.findAllByCountryAndRoleFootballAndAgentAndRolePeopleAndLeadingLegAndBirthDate(country,roleFootball,agent,
                                                                                                                          rolePeople,leadingLeg,dateOfBirth,page).iterator();

        while(iterator.hasNext()) {
            users.add(new SearchDto(iterator.next()));
        }

        return users;
    }

    @Override
    public void addUserToFavorite(long idUser) {
        User user = userDao.findById(idUser);
        Scout scout = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if(scout.getFavoriteUsers()==null) {
            scout.setFavoriteUsers(new LinkedHashSet<>());
        }
        scout.getFavoriteUsers().add(user);

        scoutDao.save(scout);
    }


    private List<Video> getVideos(User user) {
        List<Video> allVideos = videoDao.findAll();
        List<Video> videos = new LinkedList<>();
        for(Video video : allVideos) {
            if(video.getUser()!=null && video.getUser().equals(user)) {
                videos.add(video);
            }
        }
        return videos;
    }
}
