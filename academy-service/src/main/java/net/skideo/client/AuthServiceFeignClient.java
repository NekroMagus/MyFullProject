package net.skideo.client;

import net.skideo.dto.AuthDto;
import net.skideo.dto.TokenDto;
import net.skideo.model.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "localhost:9080")
public interface AuthServiceFeignClient {

    @PostMapping("/api/registration")
    ResponseEntity<TokenDto> registration(@Valid @RequestBody AuthDto authDto);

    @GetMapping("/api/auth/me")
    Auth getCurrentAuth(@RequestHeader(value="Authorization") String token);

    @PutMapping("/api/auth/data")
    void updateLoginAndPassword(@RequestHeader("Authorization") String token,@Valid @RequestBody AuthDto authDto);

}
