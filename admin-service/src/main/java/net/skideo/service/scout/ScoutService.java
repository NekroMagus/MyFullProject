package net.skideo.service.scout;

import net.skideo.dto.AdminScoutInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScoutService {

    List<AdminScoutInfoDto> findAllScouts(int page, int size);

}
