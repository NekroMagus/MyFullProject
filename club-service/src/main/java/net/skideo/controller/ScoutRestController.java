package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ScoutDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.model.enums.Region;
import net.skideo.service.scout.ScoutService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/scout")
public class ScoutRestController {

    private final ScoutService scoutService;

    @PostMapping("/{id}")
    public void addScout(@PathVariable("id") long id) {
        scoutService.addScout(id);
    }

    @DeleteMapping("/{id}")
    public void removeScout(@PathVariable("id") long id) {
        scoutService.removeScout(id);
    }

    @PutMapping("/{id}")
    public void setRegionScout(@PathVariable("id") long id, @RequestParam Region region) {
        scoutService.setRegionScout(id, region);
    }

    @GetMapping
    public SkideoListDto<ScoutDto> getScoutsByRegion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestParam Region region) {
        return new SkideoListDto<ScoutDto>(scoutService.getScoutsByRegion(region,page,size));
    }

}
