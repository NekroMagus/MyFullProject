package net.skideo.service.academy;

import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademyService {

    void save(Academy academy);

    Academy findByLogin(String login);

    AcademyAuthProjection findLoginAndPasswordByLogin(String login);

    void addPlayer(String token,long id);

    Page<Academy> getPlayers(String token,Pageable pageable);


}
