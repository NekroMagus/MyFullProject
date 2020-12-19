package net.skideo.repository;

import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy,Long> {

    Optional<Academy> findByLogin(String login);

    Optional<AcademyAuthProjection> findLoginAndPasswordByLogin(String login);

    boolean existsAcademyByLogin(String login);

    Optional<PasswordProjection> findPasswordByLogin(String login);

    Page<Academy> findPlayersByLogin(String login, Pageable pageable);

}
