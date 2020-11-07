package net.skideo.repository;

import net.skideo.model.Scout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoutRepository extends JpaRepository<Scout,Long> {

    Scout findByLogin(String login);

    boolean existsByLogin(String login);
}
