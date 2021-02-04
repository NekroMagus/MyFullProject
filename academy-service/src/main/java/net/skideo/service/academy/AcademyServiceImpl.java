package net.skideo.service.academy;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.enums.ServiceRole;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final UserService userService;
    @Autowired
    private VideoService videoService;
    private final AcademyRepository academyRepository;
    private final AuthServiceFeignClient feignClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(Academy academy) {
        academy.getInfo().setPassword(passwordEncoder.encode(academy.getInfo().getPassword()));
        academy.getInfo().setServiceRole(ServiceRole.ACADEMY);
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        Academy academy = academyRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        );

        return academy;
    }

    @Override
    public void addPlayer(long id) {
        User user = userService.getUserById(id);
        Academy currentAcademy = getCurrentAcademy();

        List<User> newListPlayers = currentAcademy.getListPlayers();
        if(newListPlayers==null) {
            newListPlayers=new LinkedList<>();
        }
        newListPlayers.add(user);

        updateListPlayers(newListPlayers);
        updateNumberPlayers(currentAcademy.getNumberPlayers()+1);
    }

    @Override
    public void updateLoginAndPassword(AuthDto authDto) {
        feignClient.updateLoginAndPassword(authDto);

        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.getInfo().setLogin(authDto.getLogin());
        dbAcademy.getInfo().setPassword(passwordEncoder.encode(authDto.getPassword()));

        save(dbAcademy);
    }

    @Override
    public void updateProfile(AcademyProfileDto academyProfileDto) {
        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.getInfo().setCity(academyProfileDto.getCity());
        dbAcademy.getInfo().setCountry(academyProfileDto.getCountry());
        dbAcademy.getInfo().setName(academyProfileDto.getTitleClub());

        academyRepository.save(dbAcademy);
    }

    @Override
    public AcademyProfileDto getProfile() {
        return academyRepository.findProfileByInfoLogin(getLoginCurrentAcademy());
    }

    @Override
    public void addVideo(AcademyVideoDto videoDto) {
        Academy currentAcademy = getCurrentAcademy();
        Video video = new Video(videoDto.getDescription(),videoDto.getLink(),currentAcademy.getInfo());
        videoService.create(video);
    }

    @Override
    public Academy getCurrentAcademy() {
        return findByLogin(getLoginCurrentAcademy());
    }

    @Override
    public String getLoginCurrentAcademy() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void updateListPlayers(List<User> listPlayers) {
        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.setListPlayers(listPlayers);

        academyRepository.save(dbAcademy);
    }

    private void updateNumberPlayers(int numberPlayers) {
        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.setNumberPlayers(numberPlayers);

        academyRepository.save(dbAcademy);
    }


}
