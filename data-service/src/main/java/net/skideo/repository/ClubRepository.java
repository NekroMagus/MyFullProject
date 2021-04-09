package net.skideo.repository;


import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.UserShortInfoClubDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Club;
import net.skideo.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ClubRepository extends BaseRepository<Club> {

    Optional<ClubProfileDto> findProfileById(long id);

    Optional<IdProjection> findClubIdByUserLogin(String login);

    Page<UserShortInfoClubDto> findFavoriteUsersByUserLogin(String login, Pageable pageable);

}
