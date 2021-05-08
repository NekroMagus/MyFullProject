package net.skideo.service.club;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.*;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.NotFoundException;
import net.skideo.model.*;
import net.skideo.repository.ClubRepository;
import net.skideo.service.city.CityService;
import net.skideo.service.player.PlayerService;
import net.skideo.service.user.UserService;
import net.skideo.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final PlayerService playerService;
    private final UserService userService;
    private final CityService cityService;
    private final PasswordEncoder encoder;

    @Override
    public Club findByLogin(String login) {
        return clubRepository.findByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Club not found")
        );
    }

    @Override
    public void save(Club club) {
        club.getUser().setPassword(encoder.encode(club.getUser().getPassword()));
        clubRepository.save(club);
    }

    @Override
    public ClubProfileDto getProfile(long id) {
        return clubRepository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Club not found")
        );
    }


    @Override
    public void addUserToFavorite(long idUser) {
        Club currentClub = getCurrentClub();
        Player player = playerService.findById(idUser);

        currentClub.getFavoritePlayers().add(player);

        clubRepository.save(currentClub);
    }

    @Override
    public Page<UserShortInfoClubDto> getFavoriteUsers(Pageable pageable) {
        final String LOGIN_CURRENT_CLUB = SecurityUtils.getLogin();
        return clubRepository.findFavoriteUsersByUserLogin(LOGIN_CURRENT_CLUB,pageable);
    }

    @Override
    public void updateProfile(ClubProfileDto profile) {
        Club dbClub = getCurrentClub();

        if(StringUtils.isNotBlank(profile.getLogoLink())) {
            dbClub.setLogoLink(profile.getLogoLink());
        }
        if(StringUtils.isNotBlank(profile.getTitleClub())) {
            dbClub.getUser().setName(profile.getTitleClub());
        }
        if(StringUtils.isNotBlank(profile.getCityName()) && StringUtils.isNotBlank(profile.getCountryName())) {
            dbClub.getUser().setCity(cityService.getCity(profile.getCityName(),profile.getCountryName()));
        }

        clubRepository.save(dbClub);
    }

    @Override
    public void updateLoginAndPassword(AuthDto authDto) {
        Club dbClub = getCurrentClub();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            if(userService.isExistsByLogin(authDto.getLogin())) {
                throw new AlreadyExistsException("User already exists");
            }
            dbClub.getUser().setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            dbClub.getUser().setPassword(authDto.getPassword());
        }

        save(dbClub);
    }

    @Override
    public Club getCurrentClub() {
        return findByLogin(SecurityUtils.getLogin());
    }

    @Override
    public long getIdByLogin(String login) {
        return clubRepository.findClubIdByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Club not found")
        ).getId();
    }


}
