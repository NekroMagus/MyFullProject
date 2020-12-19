package net.skideo.service.academy;

import net.skideo.dto.projections.PasswordProjection;
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
    private final PasswordEncoder passwordEncoder;

    public void save(Academy academy) {
        academy.setPassword(passwordEncoder.encode(academy.getPassword()));
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        Academy academy = academyRepository.findByLogin(login).orElseThrow(
                () -> new AcademyNotFoundException("Academy not found")
        );

        return academy;
    }

    @Override
    public PasswordProjection getPasswordByLogin(String login) {
        return academyRepository.findPasswordByLogin(login).orElseThrow(
                () -> new AcademyNotFoundException("Academy not found")
        );
    }

    @Override
    public boolean isExistsByLogin(String login) {
        return academyRepository.existsAcademyByLogin(login);
    }

    @Override
    public void updateListPlayers(List<User> listPlayers) {
        Academy academy = getCurrentAcademy();
        academy.setListPlayers(listPlayers);
        academyRepository.save(academy);
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
    }

    @Override
    public Page<Academy> getPlayers(Pageable pageable) {
        final String CURRENT_LOGIN = SecurityContextHolder.getContext().getAuthentication().getName();
        return academyRepository.findPlayersByLogin(CURRENT_LOGIN,pageable);
    }

    private Academy getCurrentAcademy() {
        return findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
