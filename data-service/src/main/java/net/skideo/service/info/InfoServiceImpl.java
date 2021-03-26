package net.skideo.service.info;

import lombok.RequiredArgsConstructor;
import net.skideo.repository.InfoRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InfoServiceImpl implements InfoService {

    private final InfoRepository repository;

    @Override
    public boolean isExistsByLogin(String login) {
        return repository.existsByLogin(login);
    }
}
