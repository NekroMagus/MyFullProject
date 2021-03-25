package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.Notification;
import net.skideo.model.User;
import net.skideo.model.enums.RolePeople;
import net.skideo.repository.AcademyRepository;
import net.skideo.repository.UserRepository;
import net.skideo.service.academy.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AcademyRepository academyRepository;
    private final AcademyService academyService;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    @Transactional
    public void addPlayer(long id) {
        User user = getUserById(id);
        Academy currentAcademy = academyService.getCurrentAcademy();

        currentAcademy.getPlayers().add(user);
        currentAcademy.setNumberPlayers(currentAcademy.getNumberPlayers()+1);

        academyRepository.save(currentAcademy);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserShortInfoDto> getAmateurPlayers(Pageable pageable) {
        return repository.findUsersByInfoRolePeople(RolePeople.AMATEUR,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserShortInfoAcademyDto> getMyPlayers(Pageable pageable) {
        return academyRepository.findPlayersByInfoLogin(academyService.getLoginCurrentAcademy(),pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserNSDto> findUsersByNameAndSurname(String name, String surname, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAllByInfoNameStartsWithOrInfoSurnameStartsWith(name,surname,pageable);
    }

}
