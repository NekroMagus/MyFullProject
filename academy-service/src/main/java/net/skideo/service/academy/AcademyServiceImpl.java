package net.skideo.service.academy;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.exception.AcademyNotFoundException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
                () -> new AcademyNotFoundException("Academy not found")
        );

        return academy;
    }

    @Override
    public void addPlayer(String token,long id) {
        User user = userService.getUserById(id);
        Academy currentAcademy = getCurrentAcademy(token);

        List<User> newListPlayers = currentAcademy.getListPlayers();
        if(newListPlayers==null) {
            newListPlayers=new LinkedList<>();
        }
        newListPlayers.add(user);

        updateListPlayers(token,newListPlayers);
        updateNumberPlayers(token,currentAcademy.getNumberPlayers()+1);
    }

    @Override
    public Page<UserShortInfoDto> getPlayers(String token, Pageable pageable) {
        final String CURRENT_LOGIN = feignClient.getCurrentAuth(token).getLogin();
        return academyRepository.findPlayersByInfoLogin(CURRENT_LOGIN,pageable);
    }

    @Override
    public void updateNumberPlayers(String token,int numberPlayers) {
        Academy dbAcademy = getCurrentAcademy(token);

        dbAcademy.setNumberPlayers(numberPlayers);

        academyRepository.save(dbAcademy);
    }

    @Override
    public void updateLoginAndPassword(String token,AuthDto authDto) {
        Academy dbAcademy = getCurrentAcademy(token);

        dbAcademy.getInfo().setLogin(authDto.getLogin());
        dbAcademy.getInfo().setPassword(passwordEncoder.encode(authDto.getPassword()));

        feignClient.updateLoginAndPassword(token,authDto);

        academyRepository.save(dbAcademy);
    }

    @Override
    public void updateProfile(String token,AcademyProfileDto academyProfileDto) {
        Academy dbAcademy = getCurrentAcademy(token);

        dbAcademy.getInfo().setCity(academyProfileDto.getCity());
        dbAcademy.getInfo().setCountry(academyProfileDto.getCountry());
        dbAcademy.getInfo().setName(academyProfileDto.getTitleClub());

        academyRepository.save(dbAcademy);
    }

    @Override
    public AcademyProfileDto getProfile(String token) {
        return academyRepository.findProfileByInfoLogin(getLoginCurrentAcademy(token));
    }

    @Override
    public void addVideo(String token, AcademyVideoDto videoDto) {
        Academy currentAcademy = getCurrentAcademy(token);
        Video video = new Video(videoDto.getDescription(),videoDto.getLink(),currentAcademy.getInfo());
        videoService.create(video);
    }

    private String getLoginCurrentAcademy(String token) {
        return feignClient.getCurrentAuth(token).getLogin();
    }

    @Override
    public Academy getCurrentAcademy(String token) {
        return findByLogin(getLoginCurrentAcademy(token));
    }

    private void updateListPlayers(String token,List<User> listPlayers) {
        Academy academy = getCurrentAcademy(token);
        academy.setListPlayers(listPlayers);
        academyRepository.save(academy);
    }


}
