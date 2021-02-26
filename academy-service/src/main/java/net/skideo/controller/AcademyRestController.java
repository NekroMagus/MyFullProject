package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.dto.projections.AcademyAuthProjection;
import net.skideo.dto.projections.AcademyProfileProjection;
import net.skideo.model.*;
import net.skideo.repository.AcademyRepository;
import net.skideo.repository.NotificationRepository;
import net.skideo.repository.UserRepository;
import net.skideo.repository.VideoRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final UserRepository repository;

    @GetMapping("/profile")
    public AcademyProfileDto getProfile() {
        return academyService.getProfile();
    }

    @PutMapping("/auth/data")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@RequestBody AcademyAuthDto authDto) {
        academyService.updateLoginAndPassword(token,authDto);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestBody AcademyProfileDto profileDto) {
        academyService.updateProfile(profileDto);
    }

    @GetMapping("/allU")
    public List<User> allU() {
        return repository.findAll();
    }

}
