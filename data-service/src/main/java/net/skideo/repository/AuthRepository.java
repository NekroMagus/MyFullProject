package net.skideo.repository;

import net.skideo.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findByLogin(String login);

    boolean existsByLogin(String login);
}

