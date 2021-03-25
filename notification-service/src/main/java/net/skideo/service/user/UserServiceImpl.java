package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.User;
import net.skideo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long idUser) {
        return userRepository.findById(idUser).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return findUserByLogin(getLoginCurrentUser());
    }

    private User findUserByLogin(String login) {
        return userRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    private String getLoginCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
