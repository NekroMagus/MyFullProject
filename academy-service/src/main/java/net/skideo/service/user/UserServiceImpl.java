package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.User;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.UserRepository;
import net.skideo.service.academy.AcademyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AcademyService academyService;

    @Override
    public User getUserById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable) {
        return repository.findAllByInfoRolePeople(RolePeople.AMATEUR,pageable);
    }

    @Override
    public Page<UserShortInfoDto> getPlayers(Pageable pageable) {
        return repository.findPlayersByInfoLogin(academyService.getLoginCurrentAcademy(),pageable);
    }

    @Override
    public Page<User> findUsersByNameAndSurname(String name, String surname) {
        return repository.findAllByInfoNameAndInfoSurname(name,surname);
    }
}
