package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="academy")
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String titile;
    private String city;
    private String country;
    private int numberPlayers;

    public Academy(String login, String password, String titile, String city, String country, int numberPlayers) {
        this.login = login;
        this.password = password;
        this.titile = titile;
        this.city = city;
        this.country = country;
        this.numberPlayers = numberPlayers;
    }
}
