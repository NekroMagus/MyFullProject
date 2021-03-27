package net.skideo.service.info;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Info;
import net.skideo.repository.InfoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final InfoRepository repository;

    @Override
    public Info getCurrentInfo() {
        final String LOGIN_CURRENT_INFO = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByLogin(LOGIN_CURRENT_INFO).orElseThrow(
                () -> new NotFoundException("Info not found")
        );
    }

}
