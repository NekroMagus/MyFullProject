package net.skideo.repository;

import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.ScoutPasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout,Long> {

    Optional<Scout> findByLogin(String login);

    Optional<ScoutPasswordProjection> findPasswordByLogin(String login);

    Optional<ScoutProfileProjection> findProfileByLogin(String login);

    Page<ScoutDto> findAllByClubId(long id, Pageable pageable);

    Page<ScoutDto> findAllByRegionAndClubId(String region,long id,Pageable pageable);

    boolean existsByLogin(String login);
}
