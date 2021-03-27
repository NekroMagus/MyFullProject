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
        return repository.findByLogin(getLoginCurrentInfo()).orElseThrow(
                () -> new NotFoundException("Info not found")
        );
    }

    private String getLoginCurrentInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
