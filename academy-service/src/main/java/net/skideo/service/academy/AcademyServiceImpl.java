package net.skideo.service.academy;

import net.skideo.dto.*;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
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

        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
            dbAcademy.getUser().setCity(academyProfileDto.getCity());
        }
        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
            dbAcademy.getUser().setCountry(academyProfileDto.getCountry());
        }
        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
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
        return findByLogin(getLoginCurrentAcademy());
    }

    @Override
    public String getLoginCurrentAcademy() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
