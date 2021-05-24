package net.skideo.service.club;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminClubInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.admin.SearchUserRepository;
import net.skideo.service.file.FileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {

    private final SearchUserRepository searchUserRepository;
    private final FileService<AdminClubInfoDto> fileService;

    @Override
    public SkideoListDto<AdminClubInfoDto> findAllClubs(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = searchUserRepository.findAllByServiceRole(ServiceRole.CLUB,pageable);
        return new SkideoListDto<AdminClubInfoDto>(users.stream().map(u -> new AdminClubInfoDto(u)).collect(Collectors.toList()),users.getPageable().getPageNumber(),users.getPageable().getPageSize(),users.getTotalPages(),users.getNumberOfElements());
    }

    @Override
    public void loadClubsCsvFile(String fileName, List<AdminClubInfoDto> clubs) throws IOException {
        File file = new File(fileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        fileService.writeHeaders(file);
        fileService.writeData(file,clubs);
    }
}
