package net.skideo.dao;

import net.skideo.model.Scout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoutDao extends JpaRepository<Scout,Long> {

    Scout findByLogin(String login);

    boolean existsByLogin(String login);
}
