package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegAcademyDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Academy;
import net.skideo.model.Info;
import net.skideo.service.academy.AcademyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AcademyService academyService;
    private final AuthServiceFeignClient feignClient;

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody RegAcademyDto regAcademyDto) {
        ResponseEntity<TokenDto> response = feignClient.registration(new AuthDto(regAcademyDto.getLogin(),
                                                                                 regAcademyDto.getPassword()));

        Info info = new Info();
        info.setLogin(regAcademyDto.getLogin());
        info.setPassword(regAcademyDto.getPassword());
        info.setCity(regAcademyDto.getCity());
        info.setCountry(regAcademyDto.getCountry());
        info.setName(regAcademyDto.getTitle());

        academyService.save(new Academy(info,regAcademyDto.getNumberPlayers()));

        return response;
    }




}
