package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.RolePeople;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {

    @NotBlank
    @Size(min = 6)
    private String login;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
    private RolePeople rolePeople;
}
