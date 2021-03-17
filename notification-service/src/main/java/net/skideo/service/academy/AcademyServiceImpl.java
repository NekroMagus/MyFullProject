package net.skideo.service.academy;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.repository.AcademyRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;

    @Override
    public Academy getCurrentAcademy() {
        return academyRepository.findByInfoLogin(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }
}
