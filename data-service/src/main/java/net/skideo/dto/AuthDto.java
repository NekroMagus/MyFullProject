package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    @NotBlank
    @Size(min=2)
    private String login;
    @NotBlank
    @Size(min=4)
    private String password;

}