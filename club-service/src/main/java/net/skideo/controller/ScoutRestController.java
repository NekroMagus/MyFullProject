package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.ScoutDto;
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
    public void setRegionScout(@PathVariable("id") long id, @RequestParam String region) {
        scoutService.setRegionScout(id, region);
    }

    @GetMapping
    public Page<ScoutDto> getScoutsByRegion(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size, @RequestParam String region) {
        return scoutService.getScoutsByRegion(region,page,size);
    }

}
