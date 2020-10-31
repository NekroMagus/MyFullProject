package net.skideo.service.club;

import net.skideo.dao.ClubDao;
import net.skideo.dao.ScoutDao;
import net.skideo.dao.UserDao;
import net.skideo.dao.VideoDao;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private ScoutDao scoutDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Club findByLogin(String login) {
        return clubDao.findByLogin(login);
    }

    @Override
    public void save(Club club) {
        club.setPassword(encoder.encode(club.getPassword()));
        clubDao.save(club);
    }

    @Override
    public ClubProfileDto getProfile() {
        final Club CURRENT_CLUB = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ClubProfileDto(CURRENT_CLUB);
    }

    @Override
    public List<ScoutDto> getScouts() {
        final Club CURRENT_CLUB = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Scout> allScouts = scoutDao.findAll();
        List<ScoutDto> scouts = new LinkedList<>();

        for (Scout scout : allScouts) {
            if (scout.getClub() != null && scout.getClub().equals(CURRENT_CLUB)) {
                scouts.add(new ScoutDto(scout));
            }
        }

        return scouts;
    }

    @Override
    public void addScout(long id) {
        final Club CURRENT_CLUB = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Scout scout = scoutDao.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );

        if (scout.getClub() == null) {
            scout.setClub(CURRENT_CLUB);
        }

        scoutDao.save(scout);
    }

    @Override
    public void removeScout(long id) {
        final Club CURRENT_CLUB = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Scout scout = scoutDao.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );

        if (scout.getClub().equals(CURRENT_CLUB)) {
            scout.setClub(null);
        }

        scoutDao.save(scout);
    }

    @Override
    public void setRegionScout(long id, String region) {
        final Club CURRENT_USER = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        Scout scout = scoutDao.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );

        if (scout.getClub().equals(CURRENT_USER)) {
            scout.setRegion(region);
        }

        scoutDao.save(scout);
    }

    @Override
    public List<ScoutDto> getScoutsByRegion(String region) {
        final Club CURRENT_CLUB = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Scout> allScouts = scoutDao.findAll();
        List<ScoutDto> scouts = new LinkedList<>();

        for (Scout scout : allScouts) {
            if (scout.getClub() != null && scout.getClub().equals(CURRENT_CLUB) && scout.getRegion() != null && scout.getRegion().equals(region)) {
                scouts.add(new ScoutDto(scout));
            }
        }

        return scouts;
    }

    @Override
    public void addUserToFavorite(long idUser) {
        User user = userDao.findById(idUser);
        Club club = findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (club.getFavoriteUsers() == null) {
            club.setFavoriteUsers(new LinkedHashSet<>());
        }
        club.getFavoriteUsers().add(user);

        clubDao.save(club);
    }

    @Override
    public List<VideoDto> findVideos() {
        Pageable pageable = PageRequest.of(0, 15);
        Iterator<User> users = userDao.findAll(pageable).iterator();
        List<VideoDto> videos = new LinkedList<>();

        while (users.hasNext()) {
            User user = users.next();
            if (getVideos(user).size() >= 1) {
                int random = (int) (Math.random() * getVideos(user).size() - 1);
                videos.add(new VideoDto(user, getVideos(user).get(random).getVideoLink()));
            }
        }

        return videos;
    }

    private List<Video> getVideos(User user) {
        List<Video> allVideos = videoDao.findAll();
        List<Video> videos = new LinkedList<>();
        for (Video video : allVideos) {
            if (video.getUser() != null && video.getUser().equals(user)) {
                videos.add(video);
            }
        }
        return videos;
    }


}
