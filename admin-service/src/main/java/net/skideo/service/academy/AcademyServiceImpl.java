package net.skideo.service.academy;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.admin.SearchUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AcademyServiceImpl implements AcademyService {

    private final SearchUserRepository searchUserRepository;

    @Override
    public List<AdminAcademyInfoDto> findAllAcademies(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = searchUserRepository.findAllByServiceRole(ServiceRole.ACADEMY,pageable);
        return users.stream().map(u -> new AdminAcademyInfoDto(u)).collect(Collectors.toList());
    }
}
