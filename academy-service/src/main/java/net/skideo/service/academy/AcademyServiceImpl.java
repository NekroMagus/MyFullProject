package net.skideo.service.academy;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.dto.projections.InfoIdProjection;
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
    private final AuthServiceFeignClient feignClient;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOG = Logger.getLogger(AcademyServiceImpl.class.getName());

    @Override
    public void createAcademy(Academy academy) {
        academy.getInfo().setPassword(passwordEncoder.encode(academy.getInfo().getPassword()));
        academy.getInfo().setServiceRole(ServiceRole.ACADEMY);
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        return academyRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        );
    }

    @Override
    public long getId(String login) {
        return academyRepository.findIdByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        ).getId();
    }

    @Override
    public void updateLoginAndPassword(String token,AuthDto authDto) {
        LOG.log(Level.INFO,"Updating login and password on auth-service side...");
        feignClient.updateLoginAndPassword(token,new AuthDto(authDto.getLogin(),authDto.getPassword()));
        LOG.log(Level.INFO,"Updating login and password pn auth-serivce side success");

        LOG.log(Level.INFO,"Updating login and password on academy-service side...");
        Academy dbAcademy = getCurrentAcademy();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            LOG.log(Level.INFO,"Updating login");
            dbAcademy.getInfo().setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            LOG.log(Level.INFO,"Updating password");
            dbAcademy.getInfo().setPassword(passwordEncoder.encode(authDto.getPassword()));
        }

        academyRepository.save(dbAcademy);
        LOG.log(Level.INFO,"Updating login and password on academy-service side success");
    }

    @Override
    public void updateProfile(AcademyProfileDto academyProfileDto) {
        Academy dbAcademy = getCurrentAcademy();

        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
            dbAcademy.getInfo().setCity(academyProfileDto.getCity());
        }
        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
            dbAcademy.getInfo().setCountry(academyProfileDto.getCountry());
        }
        if(StringUtils.isNotBlank(academyProfileDto.getCity())) {
            dbAcademy.getInfo().setName(academyProfileDto.getTitleClub());
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
