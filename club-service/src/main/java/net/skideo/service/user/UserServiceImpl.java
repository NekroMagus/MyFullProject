package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.UserNotFoundException;
import net.skideo.model.User;
import net.skideo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

}
