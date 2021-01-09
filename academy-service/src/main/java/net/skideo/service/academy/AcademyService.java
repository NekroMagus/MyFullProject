package net.skideo.service.academy;

import net.skideo.dto.AcademyProfileDto;
import net.skideo.dto.AuthDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.model.Academy;
import net.skideo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademyService {

    void save(Academy academy);

    Academy findByLogin(String login);

    void addPlayer(String token,long id);

    Page<UserShortInfoDto> getPlayers(String token, Pageable pageable);

    void updateNumberPlayers(String token,int numberPlayers);

    void updateLoginAndPassword(String token,AuthDto authDto);

    void updateProfile(String token,AcademyProfileDto academyProfileDto);

}
