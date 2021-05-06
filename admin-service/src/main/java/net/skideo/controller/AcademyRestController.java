package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.service.academy.AcademyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/academy")
public class AcademyRestController {

    private final AcademyService academyService;

    @GetMapping("/all")
    public List<AdminAcademyInfoDto> findAllAcademies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return academyService.findAllAcademies(page,size);
    }

}
