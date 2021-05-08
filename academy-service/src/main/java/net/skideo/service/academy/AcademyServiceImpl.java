package net.skideo.service.academy;

import net.skideo.dto.*;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import net.skideo.service.user.UserService;
import net.skideo.service.city.CityService;
import net.skideo.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
    private final UserService userService;
    private final CityService cityService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOG = Logger.getLogger(AcademyServiceImpl.class.getName());

    @Override
    public void createAcademy(Academy academy) {
        academy.getUser().setPassword(passwordEncoder.encode(academy.getUser().getPassword()));
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        return academyRepository.findByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        );
    }

    @Override
    public long getId(String login) {
        return academyRepository.findIdByUserLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        ).getId();
    }

    @Override
    public void updateLoginAndPassword(AuthDto authDto) {
        Academy dbAcademy = getCurrentAcademy();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            if(userService.isExistsByLogin(authDto.getLogin())) {
                throw new AlreadyExistsException("User already exists");
            }
            LOG.log(Level.INFO,"Updating login");
            dbAcademy.getUser().setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            LOG.log(Level.INFO,"Updating password");
            dbAcademy.getUser().setPassword(passwordEncoder.encode(authDto.getPassword()));
        }

        academyRepository.save(dbAcademy);
    }

    @Override
    public void updateProfile(AcademyProfileDto academyProfileDto) {
        Academy dbAcademy = getCurrentAcademy();

        if(StringUtils.isNotBlank(academyProfileDto.getCityName()) && StringUtils.isNotBlank(academyProfileDto.getCountryName())) {
            dbAcademy.getUser().setCity(cityService.getCity(academyProfileDto.getCityName(),academyProfileDto.getCountryName()));
        }
        if(StringUtils.isNotBlank(academyProfileDto.getTitleClub())) {
            dbAcademy.getUser().setName(academyProfileDto.getTitleClub());
        }

        academyRepository.save(dbAcademy);
    }

    @Override
    public AcademyProfileDto getProfile(long id) {
        LOG.log(Level.INFO,"Getting current academy...");
        return academyRepository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Academy not found")
        );
    }

    @Override
    public Academy getCurrentAcademy() {
        return findByLogin(SecurityUtils.getLogin());
    }


}
