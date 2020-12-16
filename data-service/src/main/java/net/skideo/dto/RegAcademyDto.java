package net.skideo.dto;

import lombok.Data;

@Data
public class RegAcademyDto {

    private String login;
    private String password;
    private String title;
    private String city;
    private String country;
    private int numberPlayers;

}
