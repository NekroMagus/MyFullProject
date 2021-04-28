package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.User;
import net.skideo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public User getCurrentUser() {
        final String LOGIN_CURRENT_USER = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByLogin(LOGIN_CURRENT_USER).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

}
