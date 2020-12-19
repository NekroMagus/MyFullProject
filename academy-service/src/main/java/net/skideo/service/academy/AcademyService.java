package net.skideo.service.academy;

import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Academy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademyService {

    void save(Academy academy);

    Academy findByLogin(String login);

    AcademyAuthProjection findLoginAndPasswordByLogin(String login);

    boolean isExistsByLogin(String login);

    PasswordProjection getPasswordByLogin(String login);

    void addPlayer(long id);

    Page<Academy> getPlayers(Pageable pageable);

}
