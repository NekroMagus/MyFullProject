package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminScoutInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.scout.ScoutService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
