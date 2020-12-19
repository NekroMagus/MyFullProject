package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.model.User;
import net.skideo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getUserById(long id) {
        return repository.getOne(id);
    }
}
