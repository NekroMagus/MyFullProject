package net.skideo.service.player;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.repository.admin.SearchUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final SearchUserRepository searchUserRepository;

    @Override
    public SkideoListDto<AdminPlayerInfoDto> findAllPlayers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> users = searchUserRepository.findAllByServiceRole(ServiceRole.PLAYER,pageable);
        return new SkideoListDto<AdminPlayerInfoDto>(users.stream().map(u -> new AdminPlayerInfoDto(u)).collect(Collectors.toList()),users.getPageable().getPageNumber(),users.getPageable().getPageSize(),users.getTotalPages(),users.getNumberOfElements());
    }
}
