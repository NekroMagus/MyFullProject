package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.RolePeople;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {

    private String login;
    private String password;
    private RolePeople rolePeople;
}
