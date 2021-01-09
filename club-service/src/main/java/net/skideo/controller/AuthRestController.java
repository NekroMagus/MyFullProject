package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.AuthDto;
import net.skideo.dto.ClubRegDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Club;
import net.skideo.service.club.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final ClubService clubService;
    private final AuthServiceFeignClient feignClient;

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody ClubRegDto regDto) {
        ResponseEntity<TokenDto> response = feignClient.registration(new AuthDto(regDto.getLogin(),
                                                                                 regDto.getPassword()));

        clubService.save(new Club(regDto.getLogin(),regDto.getPassword(),regDto.getTitle(),regDto.getLogoLink()));

        return response;
    }

}
