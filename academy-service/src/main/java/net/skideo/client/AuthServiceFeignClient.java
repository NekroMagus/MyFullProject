package net.skideo.client;

import net.skideo.dto.AuthDto;
import net.skideo.model.enums.ServiceRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "localhost:9080")
public interface AuthServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, path = "/api/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OAuth2AccessToken> registration(@RequestParam String login, @RequestParam String password, @RequestParam String clientId,
                                                   @RequestParam String clientSecret, @RequestParam String grantType, @RequestParam ServiceRole serviceRole);

    @PutMapping("/api/auth/data")
    void updateLoginAndPassword(@RequestHeader("Authorization") String token,@Valid @RequestBody AuthDto authDto);

}
