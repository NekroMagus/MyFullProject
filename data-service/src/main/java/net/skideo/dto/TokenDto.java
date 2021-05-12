package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDto extends Dto {

    private OAuth2AccessToken accessToken;

}
