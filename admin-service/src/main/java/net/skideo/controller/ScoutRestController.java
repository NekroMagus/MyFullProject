package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminScoutInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.scout.ScoutService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/scout")
public class ScoutRestController {

    private final ScoutService scoutService;

    @GetMapping("/all")
    public SkideoListDto<AdminScoutInfoDto> findAllScouts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return scoutService.findAllScouts(page,size);
    }

    @PostMapping("/all/csv")
    public void loadScoutsCsvFile(@RequestParam String fileName,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) throws IOException {
        scoutService.loadScoutsCsvFile("./csv/" + fileName,scoutService.findAllScouts(page,size).getData());
    }

}
