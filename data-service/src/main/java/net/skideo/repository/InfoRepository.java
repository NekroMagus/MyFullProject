package net.skideo.repository;

import net.skideo.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfoRepository extends JpaRepository<Info,Long> {

    Optional<Info> findByLogin(String login);

}
