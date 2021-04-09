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

    @PostMapping("/api/token")
    ResponseEntity<OAuth2AccessToken> generateToken(@RequestParam String login, @RequestParam String password, @RequestParam String clientId,
                                                    @RequestParam String clientSecret, @RequestParam String grantType);

}