package net.skideo.service.club;

import net.skideo.dto.AdminClubInfoDto;
import net.skideo.dto.base.SkideoListDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ClubService {

    SkideoListDto<AdminClubInfoDto> findAllClubs(int page, int size);

    void loadClubsCsvFile(String fileName, List<AdminClubInfoDto> clubs) throws IOException;

}
