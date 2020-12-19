package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.model.Academy;
import net.skideo.repository.AcademyRepository;
import net.skideo.service.academy.AcademyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService service;
    private final AcademyRepository repository;

    @PostMapping("/player/{id}")
    public void addPlayer(@PathVariable("id") long id) {
        service.addPlayer(id);
    }

    @GetMapping("/player/all")
    public Page<Academy> getPlayers(@RequestParam int page,@RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
        return service.getPlayers(pageable);
    }

    @GetMapping("/all")
    public List<Academy> all() {
        return repository.findAll();
    }

}
