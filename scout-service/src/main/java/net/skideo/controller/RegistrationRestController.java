package net.skideo.controller;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.RegDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Scout;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationRestController {

    private final AuthServiceFeignClient feignClient;
    private final ScoutService scoutService;

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody RegDto regDto) {
        ResponseEntity<TokenDto> response = feignClient.registration(new AuthDto(regDto.getLogin(),regDto.getPassword()));

        scoutService.save(new Scout(regDto.getLogin(), regDto.getPassword(), regDto.getName(), regDto.getSurname()));

        return response;
    }
}
