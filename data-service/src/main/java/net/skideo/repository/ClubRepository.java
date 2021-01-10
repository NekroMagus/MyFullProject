package net.skideo.repository;


import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Optional<Club> findByLogin(String login);

    Optional<ClubProfileDto> findProfileByLogin(String login);

}
