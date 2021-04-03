package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public boolean isExistsByLogin(String login) {
        return repository.existsByLogin(login);
    }

}
