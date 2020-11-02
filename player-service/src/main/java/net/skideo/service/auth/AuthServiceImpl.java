package net.skideo.service.auth;

import net.skideo.dao.UserDao;
import net.skideo.dto.UserAuthDto;
import net.skideo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isUserExists(String login) {
        return userDao.existsByLogin(login);
    }

    @Override
    public boolean isCorrectPassword(UserAuthDto userAuthDto, User user) {
        return user!=null && encoder.matches(userAuthDto.getPassword(),user.getPassword());
    }
}
