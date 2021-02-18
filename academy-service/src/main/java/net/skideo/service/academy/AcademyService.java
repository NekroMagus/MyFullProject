package net.skideo.service.academy;

import net.skideo.dto.*;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.AcademyProfileProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.model.Academy;
import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademyService {

    void createAcademy(Academy academy);

    Academy findByLogin(String login);

    void addPlayer(long id);

    void updateLoginAndPassword(AuthDto authDto);

    void updateProfile(AcademyProfileDto academyProfileDto);

    AcademyProfileDto getProfile();

    void addVideo(AcademyVideoDto videoDto);

    IdProjection getIdCurrentAcademy();

    Academy getCurrentAcademy();

    String getLoginCurrentAcademy();

}
