package net.skideo.service.user;

import net.skideo.dto.*;
import net.skideo.model.Academy;

public interface AcademyService {

    void createAcademy(Academy academy);

    Academy findByLogin(String login);

    long getId(String login);

    void updateLoginAndPassword(AuthDto authDto);

    void updateProfile(AcademyProfileDto academyProfileDto);

    AcademyProfileDto getProfile(long id);

    Academy getCurrentAcademy();

    String getLoginCurrentAcademy();

}
