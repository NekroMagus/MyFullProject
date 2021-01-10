package net.skideo.service.scout;


import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.ProfileDto;
import net.skideo.dto.ProfileUserDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.dto.projections.ProfileProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.service.video.VideoService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.ScoutRepository;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.service.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService {

    private final ScoutRepository scoutRepository;
    private final UserService userService;
    private final VideoService videoService;
    private final AuthServiceFeignClient feignClient;
    private final PasswordEncoder encoder;


    @Override
    public Scout findById(long id) {
        return scoutRepository.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );
    }

    @Override
    public Scout findByLogin(String login) {
        return scoutRepository.findByLogin(login).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );
    }

    @Override
    public ScoutProfileProjection getProfileByLogin(String login) {
        return scoutRepository.findProfileByLogin(login).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );
    }

    @Override
    public void save(Scout scout) {
        scout.setPassword(encoder.encode(scout.getPassword()));
        scoutRepository.save(scout);
    }

    @Override
    public ProfileDto getProfile(ScoutProfileProjection currentScout) {
        ProfileDto profile = new ProfileDto(currentScout);
        List<ProfileUserDto> players = new LinkedList<>();
        List<User> users = userService.findAll();

        if (users.size() >= 3) {

            for (int i = 1; i <= 3; i++) {

                int random = (int) (Math.random() * users.size() - 1);
                User user = users.get(random);

                if (videoService.findAllByUserId(user.getId()).size() >= 1) {
                    players.add(new ProfileUserDto(user));
                }
            }
        }
        return profile;
    }

    @Override
    public void updateProfile(String token,UpdateProfileDto dto) {
        Scout scout = getCurrentScout(token);
        scout.setName(dto.getName());
        scout.setSurname(dto.getSurname());
        save(scout);
    }


    @Override
    public List<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth, int page, int size) {
        List<SearchDto> users = new LinkedList<>();
        Iterator<User> iterator = userService.findAllByCountryAndRoleFootballAndHasAgentAndRolePeopleAndLeadingLegAndBirthDate(country, roleFootball, agent,
                                                                                                                               rolePeople, leadingLeg, dateOfBirth,
                                                                                                                               page, size).iterator();
        while (iterator.hasNext()) {
            users.add(new SearchDto(iterator.next()));
        }
        return users;
    }


    @Override
    public void addUserToFavorite(long idUser, Scout currentScout) {
        User user = userService.findById(idUser);

        if (currentScout.getFavoriteUsers() == null) {
            currentScout.setFavoriteUsers(new LinkedHashSet<>());
        }
        currentScout.getFavoriteUsers().add(user);

        scoutRepository.save(currentScout);
    }

    @Override
    public String getLoginCurrentScout(String token) {
        return feignClient.getCurrentAuth(token).getLogin();
    }

    @Override
    public Scout getCurrentScout(String token) {
        final String LOGIN_CURRENT_SCOUT = feignClient.getCurrentAuth(token).getLogin();
        return findByLogin(LOGIN_CURRENT_SCOUT);
    }

}
