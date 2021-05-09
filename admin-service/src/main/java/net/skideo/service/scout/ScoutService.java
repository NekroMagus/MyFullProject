package net.skideo.service.scout;

import net.skideo.dto.AdminScoutInfoDto;
import net.skideo.dto.base.SkideoListDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScoutService {

    SkideoListDto<AdminScoutInfoDto> findAllScouts(int page, int size);

}
