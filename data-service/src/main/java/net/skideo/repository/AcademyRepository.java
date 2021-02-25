package net.skideo.repository;

import net.skideo.dto.AcademyProfileDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.AcademyProfileProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Academy;
import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcademyRepository extends JpaRepository<Academy,Long> {

    Optional<Academy> findByInfoLogin(String login);

    Optional<AcademyAuthProjection> findLoginAndPasswordByInfoLogin(String login);

    AcademyProfileDto findProfileByInfoLogin(String login);

    Page<UserShortInfoDto> findPlayersByInfoLogin(String login,Pageable pageable);

    IdProjection getAcademyIdByInfoLogin(String login);

}
