package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminClubInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.club.ClubService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/club")
public class ClubRestController {

    private final ClubService clubService;

    @GetMapping("/all")
    public SkideoListDto<AdminClubInfoDto> findAllClubs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return clubService.findAllClubs(page,size);
    }

    @PostMapping("/all/csv")
    public void loadClubsCsvFile(@RequestParam String fileName,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) throws IOException {
        clubService.loadClubsCsvFile("./csv/" + fileName,clubService.findAllClubs(page,size).getData());
    }

}
