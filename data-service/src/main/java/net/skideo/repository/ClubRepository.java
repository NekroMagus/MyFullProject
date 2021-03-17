package net.skideo.repository;


import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.UserShortInfoClubDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Optional<Club> findByInfoLogin(String login);

    Optional<ClubProfileDto> findProfileByInfoLogin(String login);

    Optional<IdProjection> findClubIdByInfoLogin(String login);

    Page<UserShortInfoClubDto> findFavoriteUsersByInfoLogin(String login, Pageable pageable);

}
