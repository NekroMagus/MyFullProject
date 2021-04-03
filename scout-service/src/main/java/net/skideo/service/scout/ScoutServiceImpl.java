package net.skideo.service.scout;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import net.skideo.model.Scout;
import net.skideo.service.player.PlayerService;
import net.skideo.service.video.VideoService;
import lombok.RequiredArgsConstructor;
import net.skideo.repository.ScoutRepository;
import net.skideo.service.player.PlayerService;
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
    private final PlayerService playerService;
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
        return scoutRepository.findByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
    }

    @Override
    public void createScout(Scout scout) {
        scout.getUser().setPassword(encoder.encode(scout.getUser().getPassword()));
        scoutRepository.save(scout);
    }

    @Override
    public void updateProfile(UpdateProfileDto dto) {
        Scout scout = getCurrentScout();

        if (StringUtils.isNotBlank(dto.getName())) {
            scout.getUser().setName(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSurname())) {
            scout.getUser().setSurname(dto.getSurname());
        }

        scoutRepository.save(scout);
    }

    @Override
    public void updateLoginAndPassword(String token, AuthDto authDto) {
        Scout dbScout = getCurrentScout();

        if (StringUtils.isNotBlank(authDto.getLogin())) {
            dbScout.getUser().setLogin(authDto.getLogin());
        }
        if (StringUtils.isNotBlank(authDto.getPassword())) {
            dbScout.getUser().setPassword(encoder.encode(authDto.getPassword()));
        }

        scoutRepository.save(dbScout);
    }

    @Override
    public void addUserToFavorite(long idUser) {
        Scout currentScout = getCurrentScout();
        Player player = playerService.findById(idUser);

        if (currentScout.getFavoritePlayers() == null) {
            currentScout.setFavoritePlayers(new LinkedHashSet<>());
        }
        currentScout.getFavoritePlayers().add(player);

        scoutRepository.save(currentScout);
    }

    @Override
    public Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable) {
        final String LOGIN_CURRENT_SCOUT = getLoginCurrentScout();
        return scoutRepository.findFavoriteUsersByUserLogin(LOGIN_CURRENT_SCOUT, pageable);
    }

    @Override
    public ProfileDto getProfile(long id) {
        ProfileDto profile = scoutRepository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
        List<Player> users = playerService.findAll();

        List<ProfileUserDto> players = new LinkedList<>();

        if (users.size() >= 3) {

            for (int i = 1; i <= 3; i++) {

                int randomIndex = (int) Math.round(Math.random() * (users.size()-1));
                Player player = users.get(randomIndex);

                if (videoService.findAllByUserId(player.getUser().getId()).size() >= 1) {
                    players.add(new ProfileUserDto(player));
                }
            }
        }
        profile.setPlayers(players);

        return profile;
    }

    @Override
    public long getIdByLogin(String login) {
        return scoutRepository.findIdByUserLogin(login).orElseThrow(
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
