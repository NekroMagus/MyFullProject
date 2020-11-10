package net.skideo.repository;

import net.skideo.dto.projections.ScoutPasswordProjection;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoutRepository extends JpaRepository<Scout,Long> {

    Optional<Scout> findByLogin(String login);

    Optional<ScoutPasswordProjection> findPasswordByLogin(String login);

    Optional<ScoutProfileProjection> findProfileByLogin(String login);

    boolean existsByLogin(String login);
}
