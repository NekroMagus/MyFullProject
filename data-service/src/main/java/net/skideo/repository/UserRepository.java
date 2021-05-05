package net.skideo.repository;

import net.skideo.dto.AdminUserInfoDto;
import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    Page<AdminUserInfoDto> findAllByServiceRole(ServiceRole serviceRole, Pageable pageable);

}
