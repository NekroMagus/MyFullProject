package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.dto.base.SkideoListDto;
import net.skideo.service.academy.AcademyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/academy")
public class AcademyRestController {

    private final AcademyService academyService;

    @GetMapping("/all")
    public SkideoListDto<AdminAcademyInfoDto> findAllAcademies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) throws IOException {
        SkideoListDto<AdminAcademyInfoDto> academies = academyService.findAllAcademies(page,size);
        academyService.loadAcademiesCsvFile("test.csv",academies.getData());
        return null;
    }

    @PostMapping("/all/csv")
    public void loadAcademiesCsvFile(@RequestParam(required = false) String fileName,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) throws IOException {
        academyService.loadAcademiesCsvFile("./csv/" + "test.csv",academyService.findAllAcademies(page,size).getData());
    }

}
