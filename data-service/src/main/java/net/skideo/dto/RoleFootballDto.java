package net.skideo.dto;

import lombok.Data;
import net.skideo.dto.base.EnumDto;
import net.skideo.model.enums.RoleFootball;

@Data
public class RoleFootballDto extends EnumDto {

    private String value;

    public RoleFootballDto(RoleFootball roleFootball) {
        setCode(roleFootball.name());
        this.value=roleFootball.getValue();
    }

}
