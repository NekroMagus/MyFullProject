package net.skideo.service.user;

import net.skideo.dto.AdminUserInfoDto;
import net.skideo.model.enums.ServiceRole;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<AdminUserInfoDto> findAllByServiceRole(ServiceRole serviceRole,int page,int size);

}
