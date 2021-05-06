package net.skideo.repository.admin;

import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SearchUserRepository extends UserRepository {

    Page<User> findAllByServiceRole(ServiceRole serviceRole, Pageable pageable);

}
