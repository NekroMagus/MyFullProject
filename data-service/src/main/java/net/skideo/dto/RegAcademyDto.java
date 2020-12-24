package net.skideo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegAcademyDto {

    @Size(min=6)
    private String login;
    @Size(min=6)
    private String password;
    @NotNull
    private String title;
    @NotNull
    private String city;
    @NotNull
    private String country;
    private int numberPlayers;

}
