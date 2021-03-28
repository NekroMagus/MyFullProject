package net.skideo.repository;


import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.UserShortInfoClubDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ClubRepository extends JpaRepository<Club,Long> {

    Optional<Club> findByInfoLogin(String login);

    IdProjection findIdByInfoLogin(String login);

    Optional<ClubProfileDto> findProfileById(long id);

    Optional<IdProjection> findClubIdByInfoLogin(String login);

    Page<UserShortInfoClubDto> findFavoriteUsersByInfoLogin(String login, Pageable pageable);

    boolean existsByInfoLogin(String login);

}
