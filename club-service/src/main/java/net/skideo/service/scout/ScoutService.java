package net.skideo.service.scout;

import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScoutService {

    Scout findById(long id);

    void save(Scout scout);

    void updateClub(Scout scout, Club club);

    Page<ScoutDto> findAllByClubId(long userId, Pageable pageable);

    Page<ScoutDto> findAllByRegionAndClubId(String region,long userId,Pageable pageable);

    Page<ScoutDto> getScouts(int page,int size);

    void addScout(long id);

    void removeScout(long id);

    void setRegionScout(long id, String region);

    Page<ScoutDto> getScoutsByRegion(String region,int page,int size);
}
