package net.skideo.service.academy;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.exception.AcademyNotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.User;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final UserService userService;
    private final AcademyRepository academyRepository;
    private final AuthServiceFeignClient feignClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(Academy academy) {
        academy.getInfo().setPassword(passwordEncoder.encode(academy.getInfo().getPassword()));
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
    public AcademyAuthProjection findLoginAndPasswordByLogin(String login) {
        AcademyAuthProjection academyAuthProjection = academyRepository.findLoginAndPasswordByInfoLogin(login).orElseThrow(
                () -> new AcademyNotFoundException("Academy not found")
        );

        return academyAuthProjection;
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
    }

    @Override
    public Page<Academy> getPlayers(String token,Pageable pageable) {
        final String CURRENT_LOGIN = feignClient.getCurrentAuth(token).getLogin();
        return academyRepository.findPlayersByInfoLogin(CURRENT_LOGIN,pageable);
    }


    private Academy getCurrentAcademy(String token) {
        return findByLogin(feignClient.getCurrentAuth(token).getLogin());
    }

    private void updateListPlayers(String token,List<User> listPlayers) {
        Academy academy = getCurrentAcademy(token);
        academy.setListPlayers(listPlayers);
        academyRepository.save(academy);
    }


}
