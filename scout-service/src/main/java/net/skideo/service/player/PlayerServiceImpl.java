package net.skideo.service.player;

import net.skideo.dto.SearchDto;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.UserNotFoundException;
import net.skideo.model.Player;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Page<SearchDto> search(String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople,
                                  LeadingLeg leadingLeg, LocalDate dateOfBirth, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return playerRepository.findAllByUserCityCountryNameOrRoleFootballOrHasAgentOrRolePeopleOrLeadingLegOrBirthDate(country,roleFootball,agent,rolePeople,leadingLeg,dateOfBirth,pageable);
    }

    @Override
    public Player findById(long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Player not found")
        );
    }
}
