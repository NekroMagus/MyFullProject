package net.skideo.client;

import net.skideo.dto.AuthDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient(name = "localhost:9080")
public interface AuthServiceFeignClient {

    @PostMapping("/api/registration")
    ResponseEntity<TokenDto> registration(@Valid @RequestBody AuthDto authDto);

    @GetMapping("/api/auth/me")
    Auth getCurrentAuth(@RequestHeader("Authorization") String token);

}
