package net.skideo.service.club;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.repository.ClubRepository;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.service.scout.ScoutService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ScoutService scoutService;
    private final UserService userService;
    private final VideoService videoService;
    private final PasswordEncoder encoder;

    @Override
    public Club findById(long id) {
        return clubRepository.findById(id).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );
    }

    @Override
    public Club findByLogin(String login) {
        return clubRepository.findByLogin(login).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );
    }

    @Override
    public ClubPasswordProjection getPasswordByLogin(String login) {
        return clubRepository.findPasswordByLogin(login).orElseThrow(
                () -> new ClubNotFoundException("Club bot found")
        );
    }

    @Override
    public ClubProfileProjection getProfileByLogin(String login) {
        return clubRepository.findProfileByLogin(login).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );
    }

    @Override
    public void save(Club club) {
        club.setPassword(encoder.encode(club.getPassword()));
        clubRepository.save(club);
    }

    @Override
    public ClubProfileDto getProfile() {
        final ClubProfileProjection CURRENT_CLUB = getProfileByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ClubProfileDto(CURRENT_CLUB);
    }

    @Override
    public List<ScoutDto> getScouts(Club currentClub) {
        List<Scout> allScouts = scoutService.findAll();
        List<ScoutDto> scouts = new LinkedList<>();

        for (Scout scout : allScouts) {
            if (scout.getClub() != null && scout.getClub().equals(currentClub)) {
                scouts.add(new ScoutDto(scout));
            }
        }

        return scouts;
    }

    @Override
    public void addScout(long id, Club currentClub) {
        Scout scout = scoutService.findById(id);

        if (scout.getClub() == null) {
            scout.setClub(currentClub);
        }

        scoutService.save(scout);
    }

    @Override
    public void removeScout(long id, Club currentClub) {
        Scout scout = scoutService.findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setClub(null);
        }

        scoutService.save(scout);
    }

    @Override
    public void setRegionScout(long id, String region, Club currentClub) {
        Scout scout = scoutService.findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setRegion(region);
        }

        scoutService.save(scout);
    }

    @Override
    public List<ScoutDto> getScoutsByRegion(String region, Club currentClub) {
        List<Scout> allScouts = scoutService.findAll();
        List<ScoutDto> scouts = new LinkedList<>();

        for (Scout scout : allScouts) {
            if (scout.getClub() != null && scout.getClub().equals(currentClub) &&
                scout.getRegion() != null && scout.getRegion().equals(region)) {
                scouts.add(new ScoutDto(scout));
            }
        }
        return scouts;
    }

    @Override
    public void addUserToFavorite(long idUser, Club club) {
        User user = userService.findById(idUser);


        if (club.getFavoriteUsers() == null) {
            club.setFavoriteUsers(new LinkedHashSet<>());
        }
        club.getFavoriteUsers().add(user);

        clubRepository.save(club);
    }


    @Override
    public List<VideoDto> findVideos(int page, int size) {
        Iterator<User> users = userService.findAll(page, size).iterator();
        List<VideoDto> videos = new LinkedList<>();

        while (users.hasNext()) {
            User user = users.next();
            if (getVideos(user).size() >= 1) {
                int random = (int) (Math.random() * getVideos(user).size() - 1);
                videos.add(new VideoDto(getVideos(user).get(random)));
            }
        }

        return videos;
    }

    private List<Video> getVideos(User user) {
        List<Video> allVideos = videoService.findAll();
        List<Video> videos = new LinkedList<>();

        for (Video video : allVideos) {
            if (video.getUser() != null && video.getUser().equals(user)) {
                videos.add(video);
            }
        }

        return videos;
    }


}
