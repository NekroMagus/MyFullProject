package net.skideo.service.academy;

import net.skideo.dto.projections.PasswordProjection;
import net.skideo.model.Academy;

public interface AcademyService {

    void save(Academy academy);

    Academy findByLogin(String login);

    boolean isExistsByLogin(String login);

    PasswordProjection getPasswordByLogin(String login);

}
