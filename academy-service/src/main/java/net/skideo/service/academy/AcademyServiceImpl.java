package net.skideo.service.academy;

import net.skideo.dto.projections.PasswordProjection;
import net.skideo.exception.AcademyNotFoundException;
import net.skideo.model.Academy;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Academy academy) {
        academy.setPassword(passwordEncoder.encode(academy.getPassword()));
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        Academy academy = academyRepository.findByLogin(login).orElseThrow(
                () -> new AcademyNotFoundException("Academy not found")
        );

        return academy;
    }

    @Override
    public boolean isExistsByLogin(String login) {
        return academyRepository.existsAcademyByLogin(login);
    }

    @Override
    public PasswordProjection getPasswordByLogin(String login) {
        return academyRepository.findPasswordByLogin(login).orElseThrow(
                () -> new AcademyNotFoundException("Academy not found")
        );
    }


}
