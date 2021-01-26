package net.skideo.service.club;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.repository.ClubRepository;
import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.VideoDto;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.service.scout.ScoutService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchControls;
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
    public void save(Club club) {
        club.setPassword(encoder.encode(club.getPassword()));
        clubRepository.save(club);
    }

    @Override
    public ClubProfileDto getProfile() {
        final String LOGIN_CURRENT_CLUB = getLoginCurrentClub();
        return clubRepository.findProfileByLogin(LOGIN_CURRENT_CLUB).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );
    }

    @Override
    public Page<ScoutDto> getScouts(int page,int size) {
        Club currentClub = getCurrentClub();
        Pageable pageable = PageRequest.of(page,size);
        return scoutService.findAllByClubId(currentClub.getId(),pageable);
    }

    @Override
    public void addScout(long id) {
        Club currentClub = getCurrentClub();
        Scout scout = scoutService.findById(id);

        scoutService.updateClub(scout,currentClub);
    }

    @Override
    public void removeScout(long id) {
        Club currentClub = getCurrentClub();
        Scout scout = scoutService.findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setClub(null);
        }

        scoutService.save(scout);
    }

    @Override
    public void setRegionScout(long id, String region) {
        Club currentClub = getCurrentClub();
        Scout scout = scoutService.findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setRegion(region);
        }

        scoutService.save(scout);
    }

    @Override
    public Page<ScoutDto> getScoutsByRegion(String region,int page,int size) {
        Club currentClub = getCurrentClub();
        Pageable pageable = PageRequest.of(page,size);
        return scoutService.findAllByRegionAndClubId(region,currentClub.getId(),pageable);
    }

    @Override
    public void addUserToFavorite(long idUser) {
        Club currentClub = getCurrentClub();
        User user = userService.findById(idUser);


        if (currentClub.getFavoriteUsers() == null) {
            currentClub.setFavoriteUsers(new LinkedHashSet<>());
        }
        currentClub.getFavoriteUsers().add(user);

        clubRepository.save(currentClub);
    }


    @Override
    public List<VideoDto> findVideos(int page, int size) {
        Iterator<User> users = userService.findAll(page, size).iterator();
        List<VideoDto> videos = new LinkedList<>();

        while (users.hasNext()) {
            User user = users.next();
            if (videoService.findAllByInfoId(user.getId()).size() >= 1) {
                int random = (int) (Math.random() * videoService.findAllByInfoId(user.getId()).size() - 1);
                videos.add(new VideoDto(videoService.findAllByInfoId(user.getId()).get(random)));
            }
        }

        return videos;
    }

    @Override
    public Club getCurrentClub() {
        return findByLogin(getLoginCurrentClub());
    }

    private String getLoginCurrentClub() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
