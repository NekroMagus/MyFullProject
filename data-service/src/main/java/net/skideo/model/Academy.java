package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

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
    private String title;
    private String city;
    private String country;
    private int numberPlayers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> listPlayers;

    public Academy(String login, String password, String title, String city, String country, int numberPlayers) {
        this.login = login;
        this.password = password;
        this.title = title;
        this.city = city;
        this.country = country;
        this.numberPlayers = numberPlayers;
    }
}
