package net.skideo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RegAcademyDto {

    @Min(6)
    private String login;
    @Min(6)
    private String password;
    @NotNull
    private String title;
    @NotNull
    private String city;
    @NotNull
    private String country;
    private int numberPlayers;

}
