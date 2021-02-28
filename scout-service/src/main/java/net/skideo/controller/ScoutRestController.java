package net.skideo.controller;


import net.skideo.dto.*;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Auth;
import net.skideo.model.Scout;
import net.skideo.model.User;
import net.skideo.repository.AuthRepository;
import net.skideo.repository.ScoutRepository;
import net.skideo.repository.UserRepository;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scout")
@RequiredArgsConstructor
public class ScoutRestController {

    private final ScoutService scoutService;
    private final ScoutRepository scoutRepository;
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    // для тестов
    /* ------------------------------------------------- */

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }

    /* ------------------------------------------------- */

    @GetMapping("/profile")
    public ProfileDto getProfile() {
        return scoutService.getProfile();
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody UpdateProfileDto dto) {
        scoutService.updateProfile(dto);
    }

    @PutMapping("/auth")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AuthDto authDto) {
        scoutService.updateLoginAndPassword(token,authDto);
    }

    @PostMapping("/user/favorite/{id}")
    public void addUserToFavorite(@PathVariable("id") long id) {
        scoutService.addUserToFavorite(id);
    }

    @GetMapping("/user/favorite")
    public Page<UserShortInfoClubDto> getFavoriteUsers(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return scoutService.getFavoriteUsers(pageable);
    }

}



