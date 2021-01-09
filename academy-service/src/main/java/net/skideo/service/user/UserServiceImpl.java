package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.model.User;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getUserById(long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable) {
        return repository.findAllByInfoRolePeople(RolePeople.AMATEUR,pageable);
    }
}
