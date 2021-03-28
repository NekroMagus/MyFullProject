package net.skideo.repository;

import net.skideo.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface InfoRepository extends JpaRepository<Info,Long> {

    Optional<Info> findByLogin(String login);

    boolean existsByLogin(String login);

}
