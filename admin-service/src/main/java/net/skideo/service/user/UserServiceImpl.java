package net.skideo.service.user;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminUserInfoDto;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<AdminUserInfoDto> findAllByServiceRole(ServiceRole serviceRole,int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAllByServiceRole(serviceRole,pageable);
    }
}
