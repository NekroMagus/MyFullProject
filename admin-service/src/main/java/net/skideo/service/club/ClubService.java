package net.skideo.service.club;

import net.skideo.dto.AdminClubInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClubService {

    List<AdminClubInfoDto> findAllClubs(int page, int size);

}
