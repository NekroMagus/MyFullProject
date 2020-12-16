package net.skideo.repository;

import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy,Long> {

    Optional<Academy> findByLogin(String login);

    boolean existsAcademyByLogin(String login);

    Optional<PasswordProjection> findPasswordByLogin(String login);

}
