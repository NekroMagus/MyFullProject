package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import net.skideo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Player getUserById(long idUser) {
        return userRepository.findById(idUser).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public void save(Player player) {
        userRepository.save(player);
    }

    @Override
    public Player getCurrentUser() {
        return findUserByLogin(getLoginCurrentUser());
    }

    private Player findUserByLogin(String login) {
        return userRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    private String getLoginCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
