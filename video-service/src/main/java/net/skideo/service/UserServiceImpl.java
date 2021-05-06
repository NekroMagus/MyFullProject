package net.skideo.service;

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
    public User getCurrentUser() {
        return repository.findByLogin(getLoginCurrentUser()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    private String getLoginCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
