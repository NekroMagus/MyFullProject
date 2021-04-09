package net.skideo.repository;

import net.skideo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

}
