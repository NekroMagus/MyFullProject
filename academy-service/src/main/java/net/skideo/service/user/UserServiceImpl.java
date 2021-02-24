package net.skideo.service.user;

import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Notification;
import net.skideo.model.User;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.UserRepository;
import net.skideo.service.academy.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AcademyService academyService;

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
    public Page<UserShortInfoDto> getMyPlayers(Pageable pageable) {
        return repository.findPlayersByInfoLogin(academyService.getLoginCurrentAcademy(),pageable);
    }

    @Override
    public Page<UserNSDto> findUsersByNameAndSurname(String name, String surname, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByInfoNameStartsWithOrInfoSurnameStartsWith(name,surname,pageable);
    }

}
