package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.player.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    private final PlayerService playerService;

    @GetMapping("/all")
    public SkideoListDto<AdminPlayerInfoDto> findAllPlayers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return playerService.findAllPlayers(page,size);
    }

    @PostMapping("/all/csv")
    public void loadPlayersCsvFile(@RequestParam String fileName,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) throws IOException {
        playerService.loadPlayersCsvFile("./csv/" + fileName,playerService.findAllPlayers(page,size).getData());
    }

}
