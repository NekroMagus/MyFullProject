package net.skideo.service.scout;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.dto.projections.ScoutProjection;
import net.skideo.exception.NotFoundException;
import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Club;
import net.skideo.model.Scout;
import net.skideo.model.enums.Region;
import net.skideo.repository.ScoutRepository;
import net.skideo.service.club.ClubService;
import net.skideo.util.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ScoutServiceImpl implements ScoutService {

    private final ScoutRepository scoutRepository;
    private final ClubService clubService;

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
    public void addScout(long id) {
        Club currentClub = clubService.getCurrentClub();
        Scout scout = findById(id);

        updateClub(scout,currentClub);
    }

    @Override
    public Page<ScoutDto> getMyScouts(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        final String LOGIN_CURRENT_CLUB = SecurityUtils.getLogin();
        final long ID_CURRENT_CLUB = clubService.getIdByLogin(LOGIN_CURRENT_CLUB);
        return findAllByClubId(ID_CURRENT_CLUB,pageable);
    }


    @Override
    public void removeScout(long id) {
        Club currentClub = clubService.getCurrentClub();
        Scout scout = findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setClub(null);
        }

        save(scout);
    }

    @Override
    public void setRegionScout(long id, Region region) {
        Club currentClub = clubService.getCurrentClub();
        Scout scout = findById(id);

        if (scout.getClub().equals(currentClub)) {
            scout.setRegion(region);
        }

        save(scout);
    }

    @Override
    public Page<ScoutDto> getScoutsByRegion(Region region,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        final String LOGIN_CURRENT_CLUB = SecurityUtils.getLogin();
        final long ID_CURRENT_CLUB = clubService.getIdByLogin(LOGIN_CURRENT_CLUB);
        return scoutRepository.findAllByRegionAndClubId(region,ID_CURRENT_CLUB,pageable);
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

}
