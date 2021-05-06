package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminClubInfoDto;
import net.skideo.service.club.ClubService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/club")
public class ClubRestController {

    private final ClubService clubService;

    @GetMapping("/all")
    public List<AdminClubInfoDto> findAllClubs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return clubService.findAllClubs(page,size);
    }

}
