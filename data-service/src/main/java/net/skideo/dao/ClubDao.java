package net.skideo.dao;

import net.skideo.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubDao extends JpaRepository<Club,Long> {

    Club findByLogin(String login);

    boolean existsByLogin(String login);
}
