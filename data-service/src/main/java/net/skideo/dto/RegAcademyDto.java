package net.skideo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegAcademyDto {

    @NotBlank
    @Size(min=6)
    private String login;
    @NotBlank
    @Size(min=6)
    private String password;
    @NotBlank
    private String title;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private int numberPlayers;

}
