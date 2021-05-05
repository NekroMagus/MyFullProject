package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminUserInfoDto;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/all")
    public Page<AdminUserInfoDto> findAllByServiceRole(@RequestParam ServiceRole serviceRole,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "50") int size) {
        return userService.findAllByServiceRole(serviceRole,page,size);
    }

}
