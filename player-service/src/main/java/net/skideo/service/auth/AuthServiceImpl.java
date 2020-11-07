package net.skideo.service.auth;

import lombok.RequiredArgsConstructor;
import net.skideo.dao.UserDao;
import net.skideo.dto.UserAuthDto;
import net.skideo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Override
    public boolean isUserExists(String login) {
        return userDao.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(UserAuthDto userAuthDto, User user) {
        return user != null && passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword());
    }
}
