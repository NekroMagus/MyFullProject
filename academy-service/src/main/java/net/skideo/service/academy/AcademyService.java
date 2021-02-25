package net.skideo.service.academy;

import net.skideo.dto.*;
import net.skideo.dto.projections.InfoIdProjection;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademyService {

    void createAcademy(Academy academy);

    Academy findByLogin(String login);

    void addPlayer(long id);

    void updateLoginAndPassword(String token,AcademyAuthDto academyAuthDto);

    void updateProfile(AcademyProfileDto academyProfileDto);

    AcademyProfileDto getProfile();

    Page<UserShortInfoDto> getMyPlayers(Pageable pageable);

    InfoIdProjection getInfoIdCurrentAcademy();

    Academy getCurrentAcademy();

    String getLoginCurrentAcademy();

}
