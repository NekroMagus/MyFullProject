package net.skideo.dto.base;

import lombok.Data;

@Data
public class SkideoDto<T extends Dto> {

    private T data;

    public SkideoDto(T data) {
        this.data=data;
    }

}
