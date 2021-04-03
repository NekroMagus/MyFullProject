package net.skideo.controller;


import net.skideo.model.Player;
import net.skideo.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FindAllController {

    private final PlayerService playerService;

    @GetMapping("/all")
    public List<Player> getAll() {
        return playerService.findAll();
    }


}


