package net.skideo.repository;

import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout,Long> {

    Optional<Scout> findByLogin(String login);

    Optional<ScoutProfileProjection> findProfileByLogin(String login);

    Page<ScoutDto> findAllByClubId(long id, Pageable pageable);

    Page<ScoutDto> findAllByRegionAndClubId(String region,long id,Pageable pageable);

}
