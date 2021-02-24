package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class UserRestController {

    private final UserService userService;
    private final AcademyService academyService;

    @GetMapping
    public Page<UserNSDto> findUsersByNameAndSurname(@RequestParam String name,@RequestParam String surname,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "50") int size) {
        return userService.findUsersByNameAndSurname(name,surname,page,size);
    }

    @GetMapping("/all")
    public Page<UserShortInfoDto> getMyPlayers(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userService.getMyPlayers(pageable);
    }

    @GetMapping("/amateurs")
    public Page<UserShortInfoDto> getAmateurPlayers(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userService.getAmateurPlayers(pageable);
    }

    @PostMapping("/{id}")
    public void addPlayer(@PathVariable("id") long id) {
        academyService.addPlayer(id);
    }

}
