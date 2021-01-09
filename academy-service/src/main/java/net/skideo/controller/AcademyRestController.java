package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
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
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final AcademyRepository repository;
    private final UserService userService;
    Logger log = Logger.getLogger(AcademyRestController.class.getName());

    @PostMapping("/player/{id}")
    public void addPlayer(@RequestHeader("Authorization") String token,@PathVariable("id") long id) {
        academyService.addPlayer(token,id);
    }

    @GetMapping("/player/all")
    public Page<Academy> getPlayers(@RequestHeader("Authorization") String token,@RequestParam int page,@RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
        return academyService.getPlayers(token,pageable);
    }

    @GetMapping("/player/amateur")
    public Page<User> getAmateurPlayers(@RequestParam int page, @RequestParam int size) {
        return userService.getAmateurPlayers(PageRequest.of(page,size));
    }

    // для тестов
    @GetMapping("/all")
    public List<Academy> all() {
        return repository.findAll();
    }


}
