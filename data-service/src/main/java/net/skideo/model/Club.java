package net.skideo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.skideo.dto.ClubRegDto;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String titleClub;
    private String logoLink;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers;

    public Club(String login, String password, String titleClub, String logoLink) {
        this.login = login;
        this.password = password;
        this.titleClub = titleClub;
        this.logoLink = logoLink;
    }

    public Club(String login,String password,String title) {
        this.login = login;
        this.password = password;
        this.titleClub = title;
    }

}
