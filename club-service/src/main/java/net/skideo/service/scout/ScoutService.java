package net.skideo.service.scout;

import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.model.enums.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScoutService {

    Scout findById(long id);

    void save(Scout scout);

    void updateClub(Scout scout, Club club);

    Page<ScoutDto> findAllByClubId(long userId, Pageable pageable);

    Page<ScoutDto> getMyScouts(int page,int size);

    void addScout(long id);

    void removeScout(long id);

    void setRegionScout(long id, Region region);

    Page<ScoutDto> getScoutsByRegion(Region region,int page,int size);
}
