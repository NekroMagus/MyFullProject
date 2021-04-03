package net.skideo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "localhost:9080")
public interface AuthServiceFeignClient {

    @PostMapping("/api/token")
    ResponseEntity<OAuth2AccessToken> generateToken(@RequestParam String login, @RequestParam String password, @RequestParam String clientId,
                                                   @RequestParam String clientSecret, @RequestParam String grantType);

}
