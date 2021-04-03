package net.skideo.repository;

import net.skideo.dto.AcademyProfileDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.model.Academy;
import net.skideo.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AcademyRepository extends BaseRepository<Academy> {

    Optional<AcademyProfileDto> findProfileById(long id);

    Page<UserShortInfoAcademyDto> findPlayersByUserLogin(String login, Pageable pageable);

}
