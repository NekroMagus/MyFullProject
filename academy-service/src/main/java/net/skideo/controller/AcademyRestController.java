package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AcademyProfileDto;
import net.skideo.dto.AuthDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.model.Academy;
import net.skideo.repository.AcademyRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final AcademyRepository repository;
    private final UserService userService;

    @PostMapping("/player/{id}")
    public void addPlayer(@RequestHeader("Authorization") String token,@PathVariable("id") long id) {
        academyService.addPlayer(token,id);
    }

    @GetMapping("/player/all")
    public Page<UserShortInfoDto> getPlayers(@RequestHeader("Authorization") String token,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return academyService.getPlayers(token,pageable);
    }

    @GetMapping("/player/amateur")
    public Page<UserShortInfoDto> getAmateurPlayers(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "50") int size) {
        return userService.getAmateurPlayers(PageRequest.of(page,size));
    }

    @PutMapping("/auth/data")
    public void updateLoginAndPassword(@RequestHeader("Authorization") String token,@Valid @RequestBody AuthDto authDto) {
        academyService.updateLoginAndPassword(token,authDto);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestHeader("Authorization") String token,@Valid @RequestBody AcademyProfileDto profileDto) {
        academyService.updateProfile(token,profileDto);
    }

    // для тестов
    @GetMapping("/all")
    public List<Academy> all() {
        return repository.findAll();
    }


}
