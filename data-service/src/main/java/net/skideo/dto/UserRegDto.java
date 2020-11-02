package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.RolePeople;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {

    private String login;
    private String password;
    private RolePeople rolePeople;
    private boolean agent;

}
