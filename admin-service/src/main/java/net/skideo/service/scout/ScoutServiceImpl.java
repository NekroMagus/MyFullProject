package net.skideo.service.scout;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminScoutInfoDto;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScoutServiceImpl implements ScoutService {

    private final SearchUserRepository searchUserRepository;
    private final FileService<AdminScoutInfoDto> fileService;

    @Override
    public SkideoListDto<AdminScoutInfoDto> findAllScouts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = searchUserRepository.findAllByServiceRole(ServiceRole.SCOUT,pageable);
        return new SkideoListDto<AdminScoutInfoDto>(users.stream().map(u -> new AdminScoutInfoDto(u)).collect(Collectors.toList()),users.getPageable().getPageNumber(),users.getPageable().getPageSize(),users.getTotalPages(),users.getNumberOfElements());
    }

    @Override
    public void loadScoutsCsvFile(String fileName, List<AdminScoutInfoDto> scouts) throws IOException {
        File file = new File(fileName);
        if(!file.exists()) {
            file.createNewFile();
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName));
        fileService.writeHeaders(file,csvWriter);
        fileService.writeData(file,scouts,csvWriter);
    }
}
