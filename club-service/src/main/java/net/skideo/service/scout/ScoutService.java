package net.skideo.service.scout;

import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScoutService {

    Scout findById(long id);

    void save(Scout scout);

    Page<ScoutDto> findAllByClubId(long userId, Pageable pageable);

    Page<ScoutDto> findAllByRegionAndClubId(String region,long userId,Pageable pageable);
}
