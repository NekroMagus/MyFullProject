package net.skideo.repository;


import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.UserShortInfoClubDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Optional<Club> findByLogin(String login);

    Optional<ClubProfileDto> findProfileByLogin(String login);

    Optional<IdProjection> findClubIdByLogin(String login);

    Page<UserShortInfoClubDto> findFavoriteUsersByLogin(String login, Pageable pageable);

}
