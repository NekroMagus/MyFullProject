package net.skideo.service.scout;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.service.video.VideoService;
import lombok.RequiredArgsConstructor;
import net.skideo.repository.ScoutRepository;
import net.skideo.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService {

    private final ScoutRepository scoutRepository;
    private final UserService userService;
    private final VideoService videoService;
    private final PasswordEncoder encoder;
    private final AuthServiceFeignClient feignClient;

    @Override
    public Scout findById(long id) {
        return scoutRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
    }

    @Override
    public Scout findByLogin(String login) {
        return scoutRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
    }

    @Override
    public void createScout(Scout scout) {
        scout.getInfo().setPassword(encoder.encode(scout.getInfo().getPassword()));
        scoutRepository.save(scout);
    }

    @Override
    public void updateProfile(UpdateProfileDto dto) {
        Scout scout = getCurrentScout();

        if (StringUtils.isNotBlank(dto.getName())) {
            scout.getInfo().setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            scout.getInfo().setSurname(dto.getSurname());
        }

        scoutRepository.save(scout);
    }

    @Override
    public void updateLoginAndPassword(String token, AuthDto authDto) {
        feignClient.updateLoginAndPassword(token, authDto);

        Scout dbScout = getCurrentScout();

        if (StringUtils.isNotBlank(authDto.getLogin())) {
            dbScout.getInfo().setLogin(authDto.getLogin());
        }
        if (StringUtils.isNotBlank(authDto.getPassword())) {
            dbScout.getInfo().setPassword(encoder.encode(authDto.getPassword()));
        }

        scoutRepository.save(dbScout);
    }

    @Override
    public void addUserToFavorite(long idUser) {
        Scout currentScout = getCurrentScout();
        User user = userService.findById(idUser);

        if (currentScout.getFavoriteUsers() == null) {
            currentScout.setFavoriteUsers(new LinkedHashSet<>());
        }
        currentScout.getFavoriteUsers().add(user);

        scoutRepository.save(currentScout);
    }

    @Override
    public Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable) {
        final String LOGIN_CURRENT_SCOUT = getLoginCurrentScout();
        return scoutRepository.findFavoriteUsersByInfoLogin(LOGIN_CURRENT_SCOUT, pageable);
    }

    @Override
    public ProfileDto getProfile(long id) {
        ProfileDto profile = scoutRepository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
        List<User> users = userService.findAll();

        List<ProfileUserDto> players = new LinkedList<>();

        if (users.size() >= 3) {

            for (int i = 1; i <= 3; i++) {

                int randomIndex = (int) Math.round(Math.random() * (users.size()-1));
                User user = users.get(randomIndex);

                if (videoService.findAllByInfoId(user.getInfo().getId()).size() >= 1) {
                    players.add(new ProfileUserDto(user));
                }
            }
        }
        profile.setPlayers(players);

        return profile;
    }

    @Override
    public long getId(String login) {
        return scoutRepository.findIdByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Scout not found")
        ).getId();
    }

    @Override
    public Scout getCurrentScout() {
        return findByLogin(getLoginCurrentScout());
    }

    @Override
    public String getLoginCurrentScout() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
