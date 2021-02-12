package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotEmpty
    @Size(min = 6, max = 32)
    //@Pattern(regexp="[a-zA-Z0-9]")
    private String login;

    @NotEmpty
    @Size(min = 6, max = 32)
    private String password;

    @NotNull
    private RolePeople rolePeople;

    private boolean hasAgent;

    private ServiceRole serviceRole;


}
