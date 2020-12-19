package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.model.Academy;
import net.skideo.model.User;
import net.skideo.repository.AcademyRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final AcademyRepository repository;
    private final UserService userService;

    @PostMapping("/player/{id}")
    public void addPlayer(@PathVariable("id") long id) {
        academyService.addPlayer(id);
    }

    @GetMapping("/player/all")
    public Page<Academy> getPlayers(@RequestParam int page,@RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
        return academyService.getPlayers(pageable);
    }

    @GetMapping("/player/amateur")
    public Page<User> getAmateurPlayers(@RequestParam int page, @RequestParam int size) {
        return userService.getAmateurPlayers(PageRequest.of(page,size));
    }

    @GetMapping("/all")
    public List<Academy> all() {
        return repository.findAll();
    }

}
