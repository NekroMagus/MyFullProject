package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.player.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
