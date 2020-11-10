package net.skideo.service.scout;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Scout;
import net.skideo.repository.ScoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService {

    private final ScoutRepository scoutRepository;

    @Override
    public Scout findById(long id) {
        return scoutRepository.findById(id).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );
    }

    @Override
    public void save(Scout scout) {
        scoutRepository.save(scout);
    }

    @Override
    public List<Scout> findAll() {
        return scoutRepository.findAll();
    }
}
