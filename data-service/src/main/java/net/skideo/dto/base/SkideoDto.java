package net.skideo.dto.base;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Data
public class SkideoDto<T extends Dto> {

    private T data;

    public SkideoDto(T data) {
        this.data=data;
    }

}
