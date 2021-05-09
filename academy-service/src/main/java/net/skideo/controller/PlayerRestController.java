package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.UserNSDto;
import net.skideo.dto.UserShortInfoAcademyDto;
import net.skideo.dto.UserShortInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.player.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    private final PlayerService playerService;

    private final Logger LOG = Logger.getLogger(PlayerRestController.class.getName());

    @GetMapping
    public SkideoListDto<UserNSDto> findUsersByNameAndSurname(@RequestParam String name, @RequestParam String surname,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "50") int size) {
        LOG.log(Level.INFO,"Getting users by username " + name + " and surname " + surname);
        return new SkideoListDto<UserNSDto>(playerService.findUsersByNameAndSurname(name,surname,page,size));
    }

    @GetMapping("/all")
    public SkideoListDto<UserShortInfoAcademyDto> getMyPlayers(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "50") int size) {
        LOG.log(Level.INFO,"Getting users of current academy");
        Pageable pageable = PageRequest.of(page,size);
        return new SkideoListDto<UserShortInfoAcademyDto>(playerService.getMyPlayers(pageable));
    }

    @GetMapping("/amateurs")
    public SkideoListDto<UserShortInfoDto> getAmateurPlayers(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "50") int size) {
        LOG.log(Level.INFO,"Getting amateur players");
        Pageable pageable = PageRequest.of(page,size);
        return new SkideoListDto<UserShortInfoDto>(playerService.getAmateurPlayers(pageable));
    }

    @PostMapping("/{id}")
    public void addPlayer(@PathVariable("id") long id) {
        LOG.log(Level.INFO,"Adding player with id " + id);
        playerService.addPlayer(id);
        LOG.log(Level.INFO,"Adding player wuth id " + id + " success");
    }

}
