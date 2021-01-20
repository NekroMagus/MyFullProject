package net.skideo.client;

import net.skideo.dto.AuthDto;
import net.skideo.model.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "localhost:9080")
public interface AuthServiceFeignClient {

    @PostMapping("/api/registration")
    ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody AuthDto authDto);

    @PutMapping("/api/auth/data")
    void updateLoginAndPassword(@Valid @RequestBody AuthDto authDto);

}
