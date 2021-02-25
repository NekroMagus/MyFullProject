package net.skideo.service.scout;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.repository.ScoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService {

    private final ScoutRepository scoutRepository;

    @Override
    public Scout findById(long id) {
        return scoutRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Scout not found")
        );
    }

    @Override
    public void save(Scout scout) {
        scoutRepository.save(scout);
    }

    @Override
    public void updateClub(Scout scout, Club club) {
        if(scout.getClub()==null) {
            scout.setClub(club);
        }

        scoutRepository.save(scout);
    }

    @Override
    public Page<ScoutDto> findAllByClubId(long idUser,Pageable pageable) {
        return scoutRepository.findAllByClubId(idUser,pageable);
    }

    @Override
    public Page<ScoutDto> findAllByRegionAndClubId(String region, long idUser, Pageable pageable) {
        return scoutRepository.findAllByRegionAndClubId(region,idUser,pageable);
    }
}
