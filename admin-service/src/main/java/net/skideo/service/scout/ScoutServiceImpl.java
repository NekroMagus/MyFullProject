package net.skideo.service.scout;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminScoutInfoDto;
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
public class ScoutServiceImpl implements ScoutService {

    private final SearchUserRepository searchUserRepository;

    @Override
    public List<AdminScoutInfoDto> findAllScouts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = searchUserRepository.findAllByServiceRole(ServiceRole.SCOUT,pageable);
        return users.stream().map(u -> new AdminScoutInfoDto(u)).collect(Collectors.toList());
    }
}
