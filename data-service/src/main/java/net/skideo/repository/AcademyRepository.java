package net.skideo.repository;

import net.skideo.dto.AcademyProfileDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.dto.projections.InfoIdProjection;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AcademyRepository extends JpaRepository<Academy,Long> {

    Optional<Academy> findByInfoLogin(String login);

    Optional<IdProjection> findIdByInfoLogin(String login);

    Optional<AcademyAuthProjection> findLoginAndPasswordByInfoLogin(String login);

    Optional<AcademyProfileDto> findProfileById(long id);

    Page<UserShortInfoAcademyDto> findPlayersByInfoLogin(String login, Pageable pageable);

    Optional<InfoIdProjection> getAcademyIdByInfoLogin(String login);

    boolean existsByInfoLogin(String login);

}
