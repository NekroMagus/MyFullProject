package net.skideo.service.academy;

import net.skideo.dto.AdminAcademyInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AcademyService {

    List<AdminAcademyInfoDto> findAllAcademies(int page, int size);

}
