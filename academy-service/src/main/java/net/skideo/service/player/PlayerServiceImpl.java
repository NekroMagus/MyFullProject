package net.skideo.service.player;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.Player;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.AcademyRepository;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.util.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;
    private final AcademyRepository academyRepository;
    private final AcademyService academyService;

    private final Logger LOG = Logger.getLogger(PlayerServiceImpl.class.getName());

    @Override
    public Player getUserById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    @Transactional
    public void addPlayer(long id) {
        LOG.log(Level.INFO,"Getting user with id " + id);
        Player player = getUserById(id);
        Academy currentAcademy = academyService.getCurrentAcademy();

        currentAcademy.getPlayers().add(player);
        currentAcademy.setNumberPlayers(currentAcademy.getNumberPlayers()+1);

        academyRepository.save(currentAcademy);
    }

    @Override
    public Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable) {
        return repository.findUsersByRolePeople(RolePeople.AMATEUR,pageable);
    }

    @Override
    public Page<UserShortInfoAcademyDto> getMyPlayers(Pageable pageable) {
        return academyRepository.findPlayersByUserLogin(SecurityUtils.getLogin(),pageable);
    }

    @Override
    public Page<UserNSDto> findUsersByNameAndSurname(String name, String surname, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByUserNameStartsWithOrUserSurnameStartsWith(name,surname,pageable);
    }

}
