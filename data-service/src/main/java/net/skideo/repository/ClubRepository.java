package net.skideo.repository;

import net.skideo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club,Long> {

    Club findByLogin(String login);

    boolean existsByLogin(String login);
}
