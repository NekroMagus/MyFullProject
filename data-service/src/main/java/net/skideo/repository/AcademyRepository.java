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

public interface AcademyRepository extends JpaRepository<Academy,Long> {

    @Transactional(readOnly = true)
    Optional<Academy> findByInfoLogin(String login);

    @Transactional(readOnly = true)
    Optional<IdProjection> findIdByInfoLogin(String login);

    @Transactional(readOnly = true)
    Optional<AcademyAuthProjection> findLoginAndPasswordByInfoLogin(String login);

    @Transactional(readOnly = true)
    AcademyProfileDto findProfileById(long id);

    @Transactional(readOnly = true)
    Page<UserShortInfoAcademyDto> findPlayersByInfoLogin(String login, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<InfoIdProjection> getAcademyIdByInfoLogin(String login);

    boolean existsByInfoLogin(String login);

}
