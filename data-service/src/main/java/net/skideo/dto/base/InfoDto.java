package net.skideo.dto.base;

import lombok.Data;
import net.skideo.model.enums.RoleFootball;

@Data
public abstract class InfoDto extends Dto {

    private String name;
    private String surname;
    private RoleFootball roleFootball;

}
