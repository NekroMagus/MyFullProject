package data.service.dao;

import data.service.model.Scout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoutDao extends JpaRepository<Scout,Long> {

    Scout findByLogin(String login);

    boolean existsByLogin(String login);
}
