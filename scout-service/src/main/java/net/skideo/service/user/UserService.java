package net.skideo.service.user;

import net.skideo.dto.SearchDto;
import net.skideo.model.Player;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<Player> findAll();

    Page<SearchDto> search(String country, RoleFootball roleFootball, boolean agent,
                           RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth,
                           int page, int size);

    Player findById(long id);
}
