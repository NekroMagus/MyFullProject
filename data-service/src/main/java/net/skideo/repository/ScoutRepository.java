package net.skideo.repository;

import net.skideo.dto.ProfileDto;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.UserShortInfoClubDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout,Long> {

    Optional<Scout> findByInfoLogin(String login);

    Optional<IdProjection> findIdByInfoLogin(String login);

    Optional<ProfileDto> findProfileById(long id);

    Page<ScoutDto> findAllByClubId(long id, Pageable pageable);

    Page<ScoutDto> findAllByRegionAndClubId(String region,long id,Pageable pageable);

    Page<UserShortInfoClubDto> findFavoriteUsersByInfoLogin(String login,Pageable pageable);

    boolean existsByInfoLogin(String login);

}
